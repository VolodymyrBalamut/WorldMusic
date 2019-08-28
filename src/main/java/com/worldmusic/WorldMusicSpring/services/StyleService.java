package com.worldmusic.WorldMusicSpring.services;

import com.worldmusic.WorldMusicSpring.model.Style;
import com.worldmusic.WorldMusicSpring.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Style getStyle(int id){
        return styleRepository.findById(id).get();
    }


    public Style getTopStyle() {
        Style style;

           try {

                    style = styleRepository.findFirstByOrderByIdDesc();

           }
           catch (NullPointerException npe) {
               style = new Style(409);
            }
        return style;
    }

    public Style getFirstStyle() { return styleRepository.findFirstByOrderByIdAsc(); }

    public Style addStyle(Style style){
        styleRepository.save(style);
        return style;
    }
    public Style updateStyle(Style style){
        styleRepository.save(style);
        return style;
    }
    public boolean deleteStyle(int id){
        styleRepository.deleteById(id);
        return true;
    }

    public List<Style> getStyleByName(String name){
        return styleRepository.findByName(name);
    }
}
