package com.worldmusic.WorldMusicSpring.controllers.api;

import com.worldmusic.WorldMusicSpring.model.Artist;
import com.worldmusic.WorldMusicSpring.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArtistControllerAPI {

    @Autowired
    private ArtistService artistService;

    @GetMapping("/api/artists")
    public List<Artist> getArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/api/artists/{id}")
    public Artist getArtist(@PathVariable int id){ return artistService.getArtist(id);}


    @GetMapping("/api/artists/{name}/show")
    public List<Artist> getArtistByName(@PathVariable String name){ return artistService.getArtistByName(name); }


    @PostMapping("/api/artists")
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        Artist createdUser = artistService.addArtist(artist);
        return new ResponseEntity<Artist>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/api/artists/{id}")
    public void updateArtist(@RequestBody Artist artist) {
        artistService.updateArtist(artist);
    }

    @DeleteMapping("/api/artists/{id}")
    public void  deleteArtist(@PathVariable int id ){
        artistService.deleteArtist(id);
    }
}
