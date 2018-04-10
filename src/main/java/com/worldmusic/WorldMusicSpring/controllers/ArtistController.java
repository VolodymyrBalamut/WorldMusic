package com.worldmusic.WorldMusicSpring.controllers;

import com.worldmusic.WorldMusicSpring.dao.ArtistDAO;
import com.worldmusic.WorldMusicSpring.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArtistController {

    @Autowired
    private ArtistDAO artistDAO;

    @GetMapping("/artists")
    public List<Artist> getArtists() { return artistDAO.findAll(); }
}
