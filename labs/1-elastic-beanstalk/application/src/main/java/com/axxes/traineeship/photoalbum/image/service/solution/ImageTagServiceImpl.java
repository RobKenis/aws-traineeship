package com.axxes.traineeship.photoalbum.image.service.solution;

import com.axxes.traineeship.photoalbum.image.entity.AlbumImage;
import com.axxes.traineeship.photoalbum.image.repository.ImageRepository;
import com.axxes.traineeship.photoalbum.image.service.ImageTagService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageTagServiceImpl implements ImageTagService {

    private final ImageRepository imageRepository;

    public ImageTagServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void addTags(String albumId, String url, List<String> tags) {
        imageRepository.getAlbum(albumId).stream()
                .filter(img -> img.getImageUrl().equals(url))
                .findAny()
                .map(img -> new AlbumImage(img.getAlbumId(), img.getImageUrl(), tags))
                .ifPresent(imageRepository::save);
    }
}
