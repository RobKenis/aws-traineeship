package com.axxes.traineeship.photoalbum.image.entity;

import java.util.Date;

public class AlbumImage {

    private final String albumId;
    private final Date uploadedAt;
    private final String imageUrl;

    public AlbumImage(String albumId, String imageUrl) {
        this.albumId = albumId;
        this.uploadedAt = new Date();
        this.imageUrl = imageUrl;
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
}
