package com.axxes.traineeship.photoalbum.album.service;

import com.axxes.traineeship.photoalbum.album.entity.Album;
import com.axxes.traineeship.photoalbum.image.entity.Image;

import java.util.List;
import java.util.Optional;

public interface AlbumService {

    Optional<Album> create(String name);

    List<Album> getAll();

    Optional<Album> uploadImage(String albumId, Image image);

    Optional<Album> get(String albumId);

}
