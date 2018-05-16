package com.worldmusic.WorldMusicSpring.repositories;

import com.worldmusic.WorldMusicSpring.model.Country;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface CountryRepository extends CrudRepository<Country,Integer> {
    List<Country> findByName(String name);
    List<Country> findDistinctByClipsNotNull();

    //manual queries with JPQL
    //@Query("")
    //List<Country> findByAllOrderByIdAsc(/*Pageable pageable*/);

    List<Country> findAll(Sort sort);
}
