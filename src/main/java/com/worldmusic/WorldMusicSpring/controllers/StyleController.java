package com.worldmusic.WorldMusicSpring.controllers;

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
public class StyleController {

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

    //example
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("styles",styleService.getAllStyles());
        return "styles/index";
    }
    //index
    @GetMapping("/admin/styles")
    public String getStyles(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("styles",styleService.getAllStyles());

        return "styles/index";
    }

    //create
    @GetMapping("/admin/styles/create")
    public String getStylesCreate(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        return "styles/create";
    }

    @PostMapping("/admin/styles/create")
    public RedirectView createClip(@RequestParam String name,
                                   @RequestParam String description,
                                   Model model) {
        Style style = new Style();
        style.setName(name);
        style.setDescription(description);
        styleService.addStyle(style);
        return new RedirectView("/styles");
    }


    @GetMapping("/admin/styles/{id}")
    public String getStyle(@PathVariable int id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("style",styleService.getStyle(id));
        return "/styles/show";
    }

    @GetMapping(value = "/admin/styles/{id}/edit", name = "styles.edit")
    public String updateStyle(@PathVariable int id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("style",styleService.getStyle(id));
        return "/styles/edit";
    }

    @PostMapping("/admin/styles/{id}/edit")
    public RedirectView updateClip(@PathVariable int id,
                                   @RequestParam String name,
                                   @RequestParam String description,
                                   Model model) {
        Style style = new Style();
        style.setId(id);
        style.setName(name);
        style.setDescription(description);
        styleService.addStyle(style);
        return new RedirectView("/styles");
    }

    @PostMapping("/admin/styles/{id}/delete")
    public RedirectView  deleteStyle(@PathVariable int id, Model model ){
        styleService.deleteStyle(id);
        return new RedirectView("/styles");
    }

}
