package com.worldmusic.WorldMusicSpring.repositories;

import com.worldmusic.WorldMusicSpring.model.Style;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface StyleRepository extends CrudRepository<Style,Integer>{
    List<Style> findByName(String name);
    Style findFirstByOrderByIdDesc(); //synonym findTopByOrderByIdDesc();
    Style findFirstByOrderByIdAsc();  //synonym findTopByOrderByIdAsc();
}
