package com.axxes.traineeship.photoalbum.image.repository.solution;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.axxes.traineeship.photoalbum.image.entity.AlbumImage;
import com.axxes.traineeship.photoalbum.image.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Component
public class ImageRepositoryImpl implements ImageRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageRepositoryImpl.class);

    private final Table imagesTable;

    public ImageRepositoryImpl(@Value("${images.dynamodb.table}") String imagesTable) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.EU_WEST_1).build();
        DynamoDB dynamoDB = new DynamoDB(client);
        this.imagesTable = dynamoDB.getTable(imagesTable);
    }

    @Override
    public Optional<AlbumImage> save(AlbumImage albumImage) {
        LOGGER.debug("Saving: {}", albumImage);

        Item item = new Item()
                .withPrimaryKey("albumId", albumImage.getAlbumId())
                .withNumber("uploadedAt", albumImage.getUploadedAt().getTime())
                .withString("imageUrl", albumImage.getImageUrl());

        if (!albumImage.getTags().isEmpty()) {
            item.withStringSet("tags", new HashSet<>(albumImage.getTags()));
        }

        imagesTable.putItem(item);
        return Optional.of(albumImage);
    }

    @Override
    public List<AlbumImage> getAlbum(String albumId) {
        ItemCollection<QueryOutcome> albumImages = imagesTable.query("albumId", albumId);
        return StreamSupport.stream(albumImages.spliterator(), true)
                .map(ai -> {
                    ArrayList<String> tags = Optional.ofNullable(ai.getStringSet("tags")).map(ArrayList::new).orElse(new ArrayList<>());
                    return new AlbumImage(ai.getString("albumId"), ai.getString("imageUrl"), tags);
                })
                .collect(toList());
    }
}
