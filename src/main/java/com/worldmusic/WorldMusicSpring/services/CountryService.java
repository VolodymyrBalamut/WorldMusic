package com.worldmusic.WorldMusicSpring.services;

import com.worldmusic.WorldMusicSpring.model.Country;
import com.worldmusic.WorldMusicSpring.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries(){
        List<Country> countries = new ArrayList<>();
        countryRepository.findAll()
                .forEach(countries::add);
        return countries;
    }
}
