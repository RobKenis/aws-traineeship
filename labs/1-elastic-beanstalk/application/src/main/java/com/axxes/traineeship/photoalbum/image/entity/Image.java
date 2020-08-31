package com.axxes.traineeship.photoalbum.image.entity;

import java.util.Collections;
import java.util.List;

public class Image {

    private String url;
    private List<String> tags;

    public Image() {
    }

    public Image(String url) {
        this(url, Collections.emptyList());
    }

    public Image(String url, List<String> tags) {
        this.url = url;
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getTags() {
        return tags;
    }
}
