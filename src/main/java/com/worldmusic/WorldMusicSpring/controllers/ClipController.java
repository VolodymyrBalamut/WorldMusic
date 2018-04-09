package com.worldmusic.WorldMusicSpring.controllers;

import com.worldmusic.WorldMusicSpring.dao.ClipDAO;
import com.worldmusic.WorldMusicSpring.model.Clip;
import com.worldmusic.WorldMusicSpring.services.ClipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClipController {

    @Autowired
    private ClipService clipService;

    @GetMapping("/clips")
    public List<Clip> getClips() {
        return clipService.getAllClips();
    }

    @PostMapping("/clip")
    public ResponseEntity<Clip> createClip(@RequestBody Clip clip) {
        Clip createdUser = clipService.addClip(clip);
        return new ResponseEntity<Clip>(createdUser, HttpStatus.CREATED);
    }

}
