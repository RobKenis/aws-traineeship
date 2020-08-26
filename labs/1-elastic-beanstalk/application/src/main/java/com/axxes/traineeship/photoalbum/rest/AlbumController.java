package com.axxes.traineeship.photoalbum.rest;

import com.axxes.traineeship.photoalbum.album.entity.Album;
import com.axxes.traineeship.photoalbum.album.service.AlbumService;
import com.axxes.traineeship.photoalbum.image.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums")
    public List<Album> getAll() {
        return albumService.getAll();
    }

    @PostMapping("/albums")
    public Album create(@RequestParam String name) {
        return albumService.create(name).orElseThrow(() -> new RuntimeException("Something broke. Good luck!"));
    }

    @PutMapping("/albums/{albumId}")
    public Album uploadImage(@PathVariable String albumId, @RequestBody Image image) {
        return albumService.uploadImage(albumId, image).orElseThrow(() -> new RuntimeException("Something broke. Good luck!"));
    }
}
