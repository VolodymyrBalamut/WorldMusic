package com.worldmusic.WorldMusicSpring.services;

import com.worldmusic.WorldMusicSpring.model.Country;
import com.worldmusic.WorldMusicSpring.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Country> getCountriesWithClips(){
        List<Country> countries = new ArrayList<>();
        countryRepository.findDistinctByClipsNotNull()
                .forEach(countries::add);
        return countries;
    }

    public Country getCountry(int id){
        return countryRepository.findById(id).get();
    }


    public Country addCountry(Country country){
        countryRepository.save(country);
        return country;
    }
    public Country updateCountry(Country country){
        countryRepository.save(country);
        return country;
    }
    public boolean deleteCountry(int id){
        countryRepository.deleteById(id);
        return true;
    }

    public List<Country> getCountryByName(String name){
        return countryRepository.findByName(name);
    }
}