package com.worldmusic.WorldMusicSpring.controllers.admin;

import com.worldmusic.WorldMusicSpring.model.*;
import com.worldmusic.WorldMusicSpring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import  org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class ArtitsController {
    @Autowired
    private ArtistService clipService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private StyleService styleService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private UserService userService;

    //index
    @GetMapping("/admin/artists")
    public String getArtists(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("artists",artistService.getAllArtists());

        return "admin/artists/index";
    }

    //create
    @GetMapping("/admin/artists/create")
    public String getArtistsCreate(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("countries",countryService.getAllCountries());
        return "admin/artists/create";
    }

    @PostMapping("/admin/artists/create")
    public RedirectView createArtist(@RequestParam String name,
                                   @RequestParam String biography,
                                   @RequestParam String country_code,
                                   Model model) {
        Artist artist = new Artist();
        artist.setName(name);
        artist.setBiography(biography);
        artist.setCountry_code(country_code);
        artistService.addArtist(artist);
        return new RedirectView("/admin/artists");
    }


    @GetMapping("/admin/artists/{id}")
    public String getArtist(@PathVariable int id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Artist artist = artistService.getArtist(id);
        model.addAttribute("user",user);
        model.addAttribute("artist",artist);
        //model.addAttribute("clips",artist.getClips());
        return "admin/artists/show";
    }


    @GetMapping(value = "/admin/artists/{id}/edit", name = "artists.edit")
    public String updateArtist(@PathVariable int id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("artist",artistService.getArtist(id));
        model.addAttribute("countries",countryService.getAllCountries());
        return "admin/artists/edit";
    }

    @PostMapping("/admin/artists/{id}/edit")
    public RedirectView updateArtist(@PathVariable int id,
                                   @RequestParam String name,
                                   @RequestParam String biography,
                                   @RequestParam String country_code,
                                   Model model) {
        Artist artist = new Artist();
        artist.setId(id);
        artist.setName(name);
        artist.setBiography(biography);
        artist.setCountry_code(country_code);
        artistService.updateArtist(artist);
        return new RedirectView("/admin/artists");
    }

    @PostMapping("/admin/artists/{id}/delete")
    public RedirectView  deleteArtist(@PathVariable int id, Model model ){
        artistService.deleteArtist(id);
        return new RedirectView("/admin/artists");
    }
}
