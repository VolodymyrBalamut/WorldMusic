package com.worldmusic.WorldMusicSpring.controllers.admin;

import com.worldmusic.WorldMusicSpring.model.User;
import com.worldmusic.WorldMusicSpring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //index
    @GetMapping("/admin/users")
    public String getStyles(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("users",userService.getAllUsers());

        return "admin/users/index";
    }

    //show
    @GetMapping("/admin/users/{id}")
    public String getClip(@PathVariable int id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        User userR = userService.getUser(id);
        model.addAttribute("user",user);
        model.addAttribute("userR",userR);
        model.addAttribute("roles", new ArrayList(userR.getRoles()));

        return "admin/users/show";
    }

    //create
    @GetMapping("/admin/users/create")
    public String getUsersCreate(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        return "admin/users/create";
    }

    //create
    @PostMapping("/admin/users/create")
    public RedirectView createArtist(@RequestParam String email,
                                     @RequestParam String password,
                                     @RequestParam String name,
                                     Model model) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        userService.addUser(user);
        return new RedirectView("/admin/users");
    }

    //edit
    @GetMapping(value = "/admin/users/{id}/edit", name = "users.edit")
    public String updateUser(@PathVariable int id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        //return user data for edit for show on form
        User userR = userService.getUser(id);
        model.addAttribute("userR",userR);
        model.addAttribute("roles", new ArrayList(userR.getRoles()));
        return "admin/users/edit";
    }


    //editPost
    @PostMapping("/admin/users/{id}/edit")
    public RedirectView updateArtist(@PathVariable int id,
                                     @RequestParam String email,
                                     @RequestParam String password,
                                     @RequestParam String name,
                                     Model model) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        userService.updateUser(user);
        return new RedirectView("/admin/users");
    }

    //delete

    @PostMapping("/admin/users/{id}/delete")
    public RedirectView  deleteUser(@PathVariable int id, Model model ){
        userService.deleteUser(id);
        return new RedirectView("/admin/users");
    }
}
