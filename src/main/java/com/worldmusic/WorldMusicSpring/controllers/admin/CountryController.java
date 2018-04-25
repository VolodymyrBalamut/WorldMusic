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

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class CountryController {

    @Autowired
    private ClipService clipService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private StyleService styleService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private UserService userService;

    //index
    @GetMapping("/admin/countries")
    public String getCountries(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("countries",countryService.getAllCountries());

        return "admin/countries/index";
    }

    //create
    @GetMapping("/admin/countries/create")
    public String getCountriesCreate(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        return "admin/countries/create";
    }

    @PostMapping("/admin/countries/create")
    public RedirectView createClip(@RequestParam String code_id,
                                   @RequestParam String name,
                                   Model model) {
        Country country = new Country();
        country.setCode_id(code_id);
        country.setName(name);
        countryService.addCountry(country);
        return new RedirectView("/admin/countries");
    }


    @GetMapping("/admin/countries/{id}")
    public String getCountry(@PathVariable int id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("country",countryService.getCountry(id));
        return "admin/countries/show";
    }

    @GetMapping(value = "/admin/countries/{id}/edit", name = "countries.edit")
    public String updateStyle(@PathVariable int id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("country",countryService.getCountry(id));
        return "admin/countries/edit";
    }

    @PostMapping("/admin/countries/{id}/edit")
    public RedirectView updateClip(@PathVariable int id,
                                   @RequestParam String code_id,
                                   @RequestParam String name,
                                   Model model) {
        Country country = new Country();
        country.setId(id);
        country.setCode_id(code_id);
        country.setName(name);
        countryService.addCountry(country);
        return new RedirectView("/admin/countries");
    }

    @PostMapping("/admin/countries/{id}/delete")
    public RedirectView  deleteCountry(@PathVariable int id, Model model ){
        countryService.deleteCountry(id);
        return new RedirectView("/admin/countries");
    }

}
