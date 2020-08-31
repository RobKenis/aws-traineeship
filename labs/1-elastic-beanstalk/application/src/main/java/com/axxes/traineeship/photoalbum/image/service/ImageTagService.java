package com.axxes.traineeship.photoalbum.image.service;

import java.util.List;

public interface ImageTagService {

    void addTags(String albumId, String url, List<String> tags);

}
