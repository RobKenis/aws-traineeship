package com.axxes.traineeship.photoalbum.album.entity;

import com.axxes.traineeship.photoalbum.image.entity.Image;

import java.util.Collections;
import java.util.List;

public class Album {

    private final String id;
    private final String name;
    private final List<Image> images;

    public Album(String id, String name) {
        this(id, name, Collections.emptyList());
    }

    public Album(String id, String name, List<Image> images) {
        this.id = id;
        this.name = name;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Image> getImages() {
        return images;
    }
}
