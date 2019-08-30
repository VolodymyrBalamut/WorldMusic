package com.worldmusic.WorldMusicSpring.repositories;

import com.worldmusic.WorldMusicSpring.model.Artist;
import com.worldmusic.WorldMusicSpring.model.Clip;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClipRepository extends CrudRepository<Clip,Integer> {
    List<Clip> findByUrl(String url);
    //List<Clip> findByArtistId(int artistId);
    Clip findFirstByOrderByIdAsc();
}
