package com.axxes.traineeship.photoalbum.share.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShareMessage {

    private String imageUrl;
    private Date uploadedAt;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    @Override
    public String toString() {
        return "ShareMessage{" +
                "imageUrl='" + imageUrl + '\'' +
                ", uploadedAt=" + uploadedAt +
                '}';
    }
}
