package com.axxes.traineeship.photoalbum.image.repository;

import com.axxes.traineeship.photoalbum.image.entity.AlbumImage;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {

    Optional<AlbumImage> save(AlbumImage albumImage);

    List<AlbumImage> getAlbum(String albumId);

}
