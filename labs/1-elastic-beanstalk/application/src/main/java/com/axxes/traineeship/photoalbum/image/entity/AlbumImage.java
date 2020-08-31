package com.axxes.traineeship.photoalbum.image.entity;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AlbumImage {

    private final String albumId;
    private final Date uploadedAt;
    private final String imageUrl;
    private final List<String> tags;

    public AlbumImage(String albumId, String imageUrl) {
        this(albumId, imageUrl, Collections.emptyList());
    }

    public AlbumImage(String albumId, String imageUrl, List<String> tags) {
        this.albumId = albumId;
        this.uploadedAt = new Date();
        this.imageUrl = imageUrl;
        this.tags = tags;
    }

    public String getAlbumId() {
        return albumId;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "AlbumImage{" +
                "albumId='" + albumId + '\'' +
                ", uploadedAt=" + uploadedAt +
                ", imageUrl='" + imageUrl + '\'' +
                ", tags=" + tags +
                '}';
    }
}
