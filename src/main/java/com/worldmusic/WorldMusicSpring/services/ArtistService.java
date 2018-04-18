package com.worldmusic.WorldMusicSpring.services;

import com.worldmusic.WorldMusicSpring.model.Artist;
import com.worldmusic.WorldMusicSpring.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    public List<Artist> getAllArtists(){
        List<Artist> artists = new ArrayList<>();
        artistRepository.findAll()
                .forEach(artists::add);
        return artists;
    }
    public Artist getArtist(int id){
        return artistRepository.findById(id).get();
    }


    public Artist addArtist(Artist artist){
        artistRepository.save(artist);
        return artist;
    }
    public Artist updateArtist(Artist artist){
        artistRepository.save(artist);
        return artist;
    }
    public boolean deleteArtist(int id){
        artistRepository.deleteById(id);
        return true;
    }

    public List<Artist> getArtistByName(String name){
        return artistRepository.findByName(name);
    }
}
