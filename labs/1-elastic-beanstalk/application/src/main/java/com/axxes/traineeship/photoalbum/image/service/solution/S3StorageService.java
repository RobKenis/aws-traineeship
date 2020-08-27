package com.axxes.traineeship.photoalbum.image.service.solution;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.axxes.traineeship.photoalbum.image.entity.Image;
import com.axxes.traineeship.photoalbum.image.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

//@Service
public class S3StorageService implements ImageStorageService {

    private final AmazonS3 s3;
    private final String bucket;

    public S3StorageService(@Value("${images.s3.bucket}") String bucket) {
        s3 = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.EU_WEST_1)
                .build();
        this.bucket = bucket;
    }

    @Override
    public Optional<Image> upload(String filename, InputStream inputStream, long size) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        s3.putObject(
                new PutObjectRequest(bucket, filename, inputStream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        );
        URL imageUrl = s3.getUrl(bucket, filename);
        return Optional.of(new Image(imageUrl.toExternalForm()));
    }
}
