package com.axxes.traineeship.photoalbum.rest;

import com.axxes.traineeship.photoalbum.image.entity.Image;
import com.axxes.traineeship.photoalbum.image.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageController {

    private final ImageStorageService imageStorageService;

    @Autowired
    public ImageController(ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }

    @PostMapping("/image/_upload")
    public Image handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        return imageStorageService.upload(file.getOriginalFilename(), file.getInputStream(), file.getSize())
                .orElseThrow(() -> new RuntimeException("Failed to upload image."));
    }
}
