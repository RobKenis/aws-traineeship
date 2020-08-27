package com.axxes.traineeship.photoalbum.image.service;

import com.axxes.traineeship.photoalbum.image.entity.Image;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;

@Service
public class S3StorageService implements ImageStorageService {

    @Override
    public Optional<Image> upload(String name, InputStream inputStream, long size) {
        //TODO: Lab 2 - S3
        return Optional.empty();
    }

}
