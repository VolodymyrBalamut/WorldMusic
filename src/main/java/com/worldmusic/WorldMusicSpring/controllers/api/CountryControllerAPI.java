package com.worldmusic.WorldMusicSpring.controllers.api;

import com.worldmusic.WorldMusicSpring.model.Country;
import com.worldmusic.WorldMusicSpring.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryControllerAPI {

    @Autowired
    private CountryService countryService;

    @GetMapping("/api/countries")
    public List<Country> getCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/api/countries/{id}")
    public Country getCountry(@PathVariable int id){ return countryService.getCountry(id);}


    @GetMapping("/api/countries/{name}/show")
    public List<Country> getCountryByName(@PathVariable String name){
        return countryService.getCountryByName(name);
    }

    @PostMapping("/api/countries")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        Country createdCountry = countryService.addCountry(country);
        return new ResponseEntity<Country>(createdCountry, HttpStatus.CREATED);
    }

    @PutMapping("/api/countries/{id}")
    public ResponseEntity<Country> updateCountry(@RequestBody Country country) {
        Country createdCountry = countryService.updateCountry(country);
        return new ResponseEntity<Country>(createdCountry, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/countries/{id}")
    public void  deleteCountry(@PathVariable int id ){
        countryService.deleteCountry(id);
    }
}
