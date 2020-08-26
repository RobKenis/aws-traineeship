package com.axxes.traineeship.photoalbum.album.service;

import com.axxes.traineeship.photoalbum.album.entity.Album;
import com.axxes.traineeship.photoalbum.album.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Optional<Album> create(String name) {
        return albumRepository.save(new Album(UUID.randomUUID().toString(), name));
    }

    @Override
    public List<Album> getAll() {
        return albumRepository.getAll();
    }
}
