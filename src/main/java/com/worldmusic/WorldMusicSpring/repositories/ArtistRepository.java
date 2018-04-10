package com.worldmusic.WorldMusicSpring.repositories;

import com.worldmusic.WorldMusicSpring.model.Artist;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist,Integer>{
    List<Artist> findByName(String name);
}
