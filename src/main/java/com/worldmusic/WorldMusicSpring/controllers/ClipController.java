package com.worldmusic.WorldMusicSpring.controllers;

import com.worldmusic.WorldMusicSpring.model.Clip;
import com.worldmusic.WorldMusicSpring.services.ClipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import  org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ClipController {

    @Autowired
    private ClipService clipService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("clips",clipService.getAllClips());
        return "clips/greeting";
    }

    @GetMapping("/clips")
    public String getClips(Model model) {
        model.addAttribute("clips",clipService.getAllClips());
        return "clips/index";
    }

    @GetMapping("/clips/{id}")
    public Optional<Clip> getClip(@PathVariable int id){ return clipService.getClip(id);}


    @GetMapping("/clips/{url}/show")
    public List<Clip> getClipByName(@PathVariable String url){
        return clipService.getClipByUrl(url);
    }


    @PostMapping("/clips")
    public ResponseEntity<Clip> createClip(@RequestBody Clip clip) {
        Clip createdClip = clipService.addClip(clip);
        return new ResponseEntity<Clip>(createdClip, HttpStatus.CREATED);
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
