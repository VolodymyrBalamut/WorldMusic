package com.worldmusic.WorldMusicSpring.controllers;

import com.worldmusic.WorldMusicSpring.model.Artist;
import com.worldmusic.WorldMusicSpring.model.Clip;
import com.worldmusic.WorldMusicSpring.model.Style;
import com.worldmusic.WorldMusicSpring.services.ArtistService;
import com.worldmusic.WorldMusicSpring.services.ClipService;
import com.worldmusic.WorldMusicSpring.services.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import  org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class ClipController {

    @Autowired
    private ClipService clipService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private StyleService styleService;
    //example
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("clips",clipService.getAllClips());
        return "clips/index";
    }
    //index
    @GetMapping("/clips")
    public String getClips(Model model) {
        model.addAttribute("clips",clipService.getAllClips());
        return "clips/index";
    }

    //create
    @GetMapping("/clips/create")
    public String getClipsCreate(Model model) {
        model.addAttribute("artists",artistService.getAllArtists());
        model.addAttribute("styles",styleService.getAllStyles());
        return "clips/create";
    }
    @PostMapping("/clips/create")
    public String createClip(@RequestParam String name,
                             @RequestParam String url,
                             @RequestParam String artist_id,
                             @RequestParam String style_id,
                             Model model) {
        Clip clip = new Clip();
        clip.setName(name);
        clip.setUrl(url);
        clip.setArtist(new Artist(Integer.parseInt(artist_id),"","",""));
        clip.setStyle(new Style(Integer.parseInt(style_id),"",""));
        clipService.addClip(clip);
        return "clips/index";
    }


    @GetMapping("/clips/{id}")
    public Optional<Clip> getClip(@PathVariable int id){ return clipService.getClip(id);}


    @GetMapping("/clips/{url}/show")
    public List<Clip> getClipByName(@PathVariable String url){
        return clipService.getClipByUrl(url);
    }




    @PutMapping("/clips/{id}/update")
    public ResponseEntity<Clip> updateClip(@RequestBody Clip clip) {
        Clip createdClip = clipService.updateClip(clip);
        return new ResponseEntity<Clip>(createdClip, HttpStatus.CREATED);
    }

    @DeleteMapping("/clips/{id}/delete")
    public void  deleteClip(@PathVariable int id ){
        clipService.deleteClip(id);
    }
}
