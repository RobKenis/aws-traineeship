package com.axxes.traineeship.photoalbum.tags;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import com.axxes.traineeship.photoalbum.image.entity.AlbumImage;
import com.axxes.traineeship.photoalbum.image.service.ImageTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class RekognitionTagsListener implements TagsListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RekognitionTagsListener.class);

    private final AmazonRekognition rekognition;
    private final String imagesBucket;

    private final ImageTagService imageTagService;

    public RekognitionTagsListener(@Value("${images.s3.bucket}") String imagesBucket, ImageTagService imageTagService) {
        this.imageTagService = imageTagService;
        rekognition = AmazonRekognitionClientBuilder.standard().withRegion(Regions.EU_WEST_1).build();
        this.imagesBucket = imagesBucket;
    }

    @Override
    public void imageUploaded(AlbumImage image) {
        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image().withS3Object(new S3Object()
                        .withName(extractKey(image.getImageUrl()))
                        .withBucket(imagesBucket)))
                .withMaxLabels(10).withMinConfidence(75F);
        DetectLabelsResult result = rekognition.detectLabels(request);
        List<Label> labels = result.getLabels();
        imageTagService.addTags(
                image.getAlbumId(),
                image.getImageUrl(),
                labels.stream().map(Label::getName).collect(toList())
        );
    }

    //Dirty. Dont do this
    private String extractKey(String url) {
        String[] parts = url.split("/");
        return parts[parts.length - 1];
    }
}
