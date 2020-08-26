package com.axxes.traineeship.photoalbum.album.service;

import com.axxes.traineeship.photoalbum.album.entity.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {

    Optional<Album> create(String name);

    List<Album> getAll();

}
