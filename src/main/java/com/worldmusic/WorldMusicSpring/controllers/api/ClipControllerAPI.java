package com.worldmusic.WorldMusicSpring.controllers.api;

import com.worldmusic.WorldMusicSpring.model.Clip;
import com.worldmusic.WorldMusicSpring.services.ClipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClipControllerAPI {

    @Autowired
    private ClipService clipService;

    @GetMapping("/api/clips")
    public List<Clip> getClips() {
        return clipService.getAllClips();
    }

    @GetMapping("/api/clips/{id}")
    public Clip getClip(@PathVariable int id){ return clipService.getClip(id);}


    @GetMapping("/api/clips/{url}/show")
    public List<Clip> getClipByName(@PathVariable String url){
        return clipService.getClipByUrl(url);
    }

    @GetMapping("/api/clips/count")
    public long getClipCount(){
        return clipService.getCount();
    }


    @PostMapping("/api/clips")
    public ResponseEntity<Clip> createClip(@RequestBody Clip clip) {
        Clip createdClip = clipService.addClip(clip);
        return new ResponseEntity<Clip>(createdClip, HttpStatus.CREATED);
    }


    @PutMapping("/api/clips/{id}")
    public ResponseEntity<Clip> updateClip(@RequestBody Clip clip) {
        Clip createdClip = clipService.updateClip(clip);
        return new ResponseEntity<Clip>(createdClip, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/clips/{id}")
    public void  deleteClip(@PathVariable int id ){
        clipService.deleteClip(id);
    }
}
