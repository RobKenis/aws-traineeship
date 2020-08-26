package com.axxes.traineeship.photoalbum.album.repository;

import com.axxes.traineeship.photoalbum.album.entity.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository {

    Optional<Album> save(Album album);

    List<Album> getAll();

}
