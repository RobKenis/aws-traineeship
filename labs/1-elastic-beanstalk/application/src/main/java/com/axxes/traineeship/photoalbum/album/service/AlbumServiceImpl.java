package com.axxes.traineeship.photoalbum.album.service;

import com.axxes.traineeship.photoalbum.album.entity.Album;
import com.axxes.traineeship.photoalbum.album.repository.AlbumRepository;
import com.axxes.traineeship.photoalbum.image.entity.AlbumImage;
import com.axxes.traineeship.photoalbum.image.entity.Image;
import com.axxes.traineeship.photoalbum.image.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository, ImageRepository imageRepository) {
        this.albumRepository = albumRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public Optional<Album> create(String name) {
        return albumRepository.save(new Album(UUID.randomUUID().toString(), name));
    }

    @Override
    public List<Album> getAll() {
        return albumRepository.getAll().stream()
                .map(album -> get(album.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    @Override
    public Optional<Album> uploadImage(String albumId, Image image) {
        imageRepository.save(new AlbumImage(albumId, image.getUrl()));
        return get(albumId);
    }

    @Override
    public Optional<Album> get(String albumId) {
        Optional<Album> album = albumRepository.get(albumId);
        List<AlbumImage> albumImages = imageRepository.getAlbum(albumId);
        List<Image> images = albumImages.stream().map(ai -> new Image(ai.getImageUrl())).collect(toList());
        return album.map(a -> new Album(a.getId(), a.getName(), images));
    }
}