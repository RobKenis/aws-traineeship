package com.axxes.traineeship.photoalbum.album.repository.solution;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.axxes.traineeship.photoalbum.album.entity.Album;
import com.axxes.traineeship.photoalbum.album.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Component
public class AlbumRepositoryImpl implements AlbumRepository {

    private final Table albumsTable;

    public AlbumRepositoryImpl(@Value("${albums.dynamodb.table}") String albumsTable) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.EU_WEST_1).build();
        DynamoDB dynamoDB = new DynamoDB(client);
        this.albumsTable = dynamoDB.getTable(albumsTable);
    }

    @Override
    public Optional<Album> save(Album album) {
        Item item = new Item()
                .withPrimaryKey("id", album.getId())
                .withString("name", album.getName());
        albumsTable.putItem(item);
        return Optional.of(album);
    }

    @Override
    public List<Album> getAll() {
        return StreamSupport.stream(albumsTable.scan().spliterator(), true)
                .map(item -> new Album(item.getString("id"), item.getString("name")))
                .collect(toList());
    }

    @Override
    public Optional<Album> get(String id) {
        return Optional.ofNullable(albumsTable.getItem("id", id))
                .map(item -> new Album(item.getString("id"), item.getString("name")));
    }
}
