package com.axxes.traineeship.photoalbum.album.repository;

import com.axxes.traineeship.photoalbum.album.entity.Album;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class AlbumRepositoryImpl implements AlbumRepository {

    @Override
    public Optional<Album> save(Album album) {
        //TODO: Lab 3 - DynamoDB - part 1
        return Optional.empty();
    }

    @Override
    public List<Album> getAll() {
        //TODO: Lab 3 - DynamoDB - part 1
        return Collections.emptyList();
    }

    @Override
    public Optional<Album> get(String id) {
        //TODO: Lab 3 - DynamoDB - part 2
        return Optional.empty();
    }
}
