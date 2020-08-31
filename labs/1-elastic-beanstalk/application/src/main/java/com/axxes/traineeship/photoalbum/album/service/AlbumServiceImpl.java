package com.axxes.traineeship.photoalbum.album.service;

import com.axxes.traineeship.photoalbum.album.entity.Album;
import com.axxes.traineeship.photoalbum.album.repository.AlbumRepository;
import com.axxes.traineeship.photoalbum.image.entity.AlbumImage;
import com.axxes.traineeship.photoalbum.image.entity.Image;
import com.axxes.traineeship.photoalbum.image.repository.ImageRepository;
import com.axxes.traineeship.photoalbum.share.ShareListener;
import com.axxes.traineeship.photoalbum.tags.TagsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class AlbumServiceImpl implements AlbumService, ShareService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlbumServiceImpl.class);

    private final AlbumRepository albumRepository;
    private final ImageRepository imageRepository;
    private final ShareListener shareListener; // This is for lab 5, don't worry about it yet.
    private final TagsListener tagsListener;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository, ImageRepository imageRepository, ShareListener shareListener, TagsListener tagsListener) {
        this.albumRepository = albumRepository;
        this.imageRepository = imageRepository;
        this.shareListener = shareListener;
        this.tagsListener = tagsListener;
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
        final Optional<AlbumImage> albumImage = imageRepository.save(new AlbumImage(albumId, image.getUrl()));
        albumImage.ifPresent(shareListener::imageAdded);
        albumImage.ifPresent(tagsListener::imageUploaded);
        return get(albumId);
    }

    @Override
    public Optional<Album> get(String albumId) {
        Optional<Album> album = albumRepository.get(albumId);
        List<AlbumImage> albumImages = imageRepository.getAlbum(albumId);
        List<Image> images = albumImages.stream().map(ai -> new Image(ai.getImageUrl(), ai.getTags())).collect(toList());
        return album.map(a -> new Album(a.getId(), a.getName(), images));
    }

    @Override
    public void addToShared(String imageUrl) {
        // This is a horrible way to query on "name" by the way.
        // A  proper solution is to add a secondary index to query on name
        final Optional<Album> album = albumRepository.getAll().stream()
                .filter(a -> a.getName().equals("shared"))
                .findAny();
        if (album.isPresent()){
            //Don't send a new notification to the listener
            imageRepository.save(new AlbumImage(album.get().getId(), imageUrl));
        } else {
            LOGGER.warn("No shared album found.");
        }
    }
}
