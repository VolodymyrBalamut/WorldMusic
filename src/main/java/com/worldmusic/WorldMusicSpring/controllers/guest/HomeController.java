package com.worldmusic.WorldMusicSpring.controllers.guest;

import com.worldmusic.WorldMusicSpring.services.ClipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ClipService clipService;

    @GetMapping("/")
    public String home(Model model){

        return "guest/home";
    }
}
