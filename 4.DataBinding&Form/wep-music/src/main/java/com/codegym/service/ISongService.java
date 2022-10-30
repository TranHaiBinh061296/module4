package com.codegym.service;

import com.codegym.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();

    void save(Song product);

    Song findById(int id);

    void update(int id, Song song);

    void remove(int id);

}
