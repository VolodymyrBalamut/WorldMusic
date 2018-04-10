package com.worldmusic.WorldMusicSpring.services;

import com.worldmusic.WorldMusicSpring.model.Style;
import com.worldmusic.WorldMusicSpring.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StyleService {

    @Autowired
    private StyleRepository styleRepository;

    public List<Style> getAllStyles(){
        List<Style> styles = new ArrayList<>();
        styleRepository.findAll()
                .forEach(styles::add);
        return styles;
    }

    public Style addStyle(Style style){
        styleRepository.save(style);
        return style;
    }
}