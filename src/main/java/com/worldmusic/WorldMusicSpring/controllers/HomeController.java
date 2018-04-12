package com.worldmusic.WorldMusicSpring.controllers;

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
public class HomeController {

    @Autowired
    private ClipService clipService;

    @GetMapping("/")
    public String home(Model model){
        return "index";
    }
}
