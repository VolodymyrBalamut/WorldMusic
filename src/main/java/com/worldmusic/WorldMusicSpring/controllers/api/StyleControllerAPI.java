
package com.worldmusic.WorldMusicSpring.controllers.api;

import com.worldmusic.WorldMusicSpring.dao.StyleDAO;
import com.worldmusic.WorldMusicSpring.model.Style;
import com.worldmusic.WorldMusicSpring.services.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StyleControllerAPI {

    @Autowired
    private StyleService styleService;

    @GetMapping("/styles")
    public List<Style> getStyles() {
        return styleService.getAllStyles();
    }

    @GetMapping("/styles/{id}")
    public Style getStyle(@PathVariable int id){ return styleService.getStyle(id);}


    @GetMapping("/styles/{name}/show")
    public List<Style> getStyleByName(@PathVariable String name){
        return styleService.getStyleByName(name);
    }


    @PostMapping("/styles")
    public ResponseEntity<Style> createStyle(@RequestBody Style style) {
        Style createdStyle = styleService.addStyle(style);
        return new ResponseEntity<Style>(createdStyle, HttpStatus.CREATED);
    }

    @PutMapping("/styles/{id}")
    public void updateStyle(@RequestBody Style style) {
        styleService.updateStyle(style);
    }

    @DeleteMapping("/styles/{id}")
    public void  deleteStyle(@PathVariable int id ){
        styleService.deleteStyle(id);
    }
}
