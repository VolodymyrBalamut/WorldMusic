package com.worldmusic.WorldMusicSpring.controllers;

import com.worldmusic.WorldMusicSpring.dao.StyleDAO;
import com.worldmusic.WorldMusicSpring.model.Style;
import com.worldmusic.WorldMusicSpring.services.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StyleController {

    @Autowired
    private StyleService styleService;

    @GetMapping("/styles")
    public List<Style> getStyles() {
        return styleService.getAllStyles();
    }

    @PostMapping("/styles")
    public ResponseEntity<Style> createStyle(@RequestBody Style style) {
        Style createdUser = styleService.addStyle(style);
        return new ResponseEntity<Style>(createdUser, HttpStatus.CREATED);
    }

}