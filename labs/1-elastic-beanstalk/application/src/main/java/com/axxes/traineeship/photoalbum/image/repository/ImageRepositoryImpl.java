package com.axxes.traineeship.photoalbum.image.repository;

import com.axxes.traineeship.photoalbum.image.entity.AlbumImage;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ImageRepositoryImpl implements ImageRepository {

    @Override
    public Optional<AlbumImage> save(AlbumImage albumImage) {
        //TODO: Lab 3 - DynamoDB - part 2
        return Optional.empty();
    }

    @Override
    public List<AlbumImage> getAlbum(String albumId) {
        //TODO: Lab 3 - DynamoDB - part 2
        return Collections.emptyList();
    }
}
