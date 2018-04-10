package com.worldmusic.WorldMusicSpring.controllers;

import com.worldmusic.WorldMusicSpring.dao.ClipDAO;
import com.worldmusic.WorldMusicSpring.model.Clip;
import com.worldmusic.WorldMusicSpring.services.ClipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClipController {

    @Autowired
    private ClipService clipService;

    @GetMapping("/clips")
    public List<Clip> getClips() {
        return clipService.getAllClips();
    }

    @GetMapping("/clips/{id}")
    public Optional<Clip> getClip(@PathVariable int id){ return clipService.getClip(id);}


    @GetMapping("/clips/{url}/show")
    public List<Clip> getClipByName(@PathVariable String url){
        return clipService.getClipByUrl(url);
    }

    @GetMapping("/clips/count")
    public long getClipCount(){
        return clipService.getCount();
    }


    @PostMapping("/clips")
    public ResponseEntity<Clip> createClip(@RequestBody Clip clip) {
        Clip createdUser = clipService.addClip(clip);
        return new ResponseEntity<Clip>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/clips/{id}")
    public void updateClip(@RequestBody Clip clip) {
         clipService.updateClip(clip);
    }

    @DeleteMapping("/clips/{id}")
    public void  deleteClip(@PathVariable int id ){
        clipService.deleteClip(id);
    }
}
