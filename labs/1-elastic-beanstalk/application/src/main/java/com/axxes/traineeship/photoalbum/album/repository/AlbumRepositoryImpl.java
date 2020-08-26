package com.axxes.traineeship.photoalbum.album.repository;

import com.axxes.traineeship.photoalbum.album.entity.Album;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@Component
public class AlbumRepositoryImpl implements AlbumRepository {

    @Override
    public Optional<Album> save(Album album) {
        //TODO: Lab 3 - DynamoDB
        return Optional.empty();
    }

    @Override
    public List<Album> getAll() {
        //TODO: Lab 3 - DynamoDB
        return Collections.emptyList();
    }

    @Override
    public Optional<Album> get(String id) {
        //TODO: Lab 3 - DynamoDB
        return Optional.empty();
    }
}
