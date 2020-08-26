package com.axxes.traineeship.photoalbum.rest;

import com.axxes.traineeship.photoalbum.album.entity.Album;
import com.axxes.traineeship.photoalbum.album.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums")
    public List<Album> getAll(){
        return albumService.getAll();
    }

    @PostMapping("/albums")
    public Album create(@RequestParam String name){
        return albumService.create(name).orElseThrow(() -> new RuntimeException("Something broke. Good luck!"));
    }
}
