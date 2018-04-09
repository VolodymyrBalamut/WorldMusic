package com.worldmusic.WorldMusicSpring.controllers;

import com.worldmusic.WorldMusicSpring.dao.ClipDAO;
import com.worldmusic.WorldMusicSpring.model.Clip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClipController {

    @Autowired
    private ClipDAO clipDAO;

    @GetMapping("/clips")
    public List<Clip> getClips() {
        return clipDAO.findAll();
    }
}
