package com.axxes.traineeship.photoalbum.image.service;

import com.axxes.traineeship.photoalbum.image.entity.Image;

import java.io.InputStream;
import java.util.Optional;

public interface ImageStorageService {

    Optional<Image> upload(String filename, InputStream inputStream, long size);

}
