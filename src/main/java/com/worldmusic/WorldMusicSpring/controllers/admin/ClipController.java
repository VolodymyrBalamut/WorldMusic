package com.worldmusic.WorldMusicSpring.controllers.admin;

import com.worldmusic.WorldMusicSpring.model.*;
import com.worldmusic.WorldMusicSpring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import  org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class ClipController {

    @Autowired
    private ClipService clipService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private StyleService styleService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;
    /*//example
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("clips",clipService.getAllClips());
        return "clips/index";
    }*/
    //index
    @GetMapping("/admin/clips")
    public String getClips(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("clips",clipService.getAllClips());

        return "admin/clips/index";
    }

    //create
    @GetMapping("/admin/clips/create")
    public String getClipsCreate(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("artists",artistService.getAllArtists());
        model.addAttribute("styles",styleService.getAllStyles());
        model.addAttribute("countries",countryService.getAllCountries());
        return "admin/clips/create";
    }

    @PostMapping("/admin/clips/create")
    public RedirectView createClip(@RequestParam String name,
                             @RequestParam String url,
                             @RequestParam String artist_id,
                             @RequestParam String style_id,
                             @RequestParam String country_id,
                             Model model) {
        Clip clip = new Clip();
        List<Style> styles = new ArrayList<>();
        styles.add(new Style(Integer.parseInt(style_id)));

        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist(Integer.parseInt(artist_id)));


        clip.setName(name);
        clip.setUrl(url);
        clip.setClipArtists(artists);
        clip.setClipStyles(styles);
        clip.setCountry(new Country(Integer.parseInt(country_id)));
        clipService.addClip(clip);
        return new RedirectView("/admin/clips/"  + clip.getId());
    }


    @GetMapping("/admin/clips/{id}")
    public String getClip(@PathVariable int id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("clip",clipService.getClip(id));
        model.addAttribute("commentCount", clipService.getCommentCount(id));
        return "admin/clips/show";
    }

    /*
    @GetMapping("/clips/{url}/show")
    public List<Clip> getClipByName(@PathVariable String url){
        return clipService.getClipByUrl(url);
    }*/


    @GetMapping(value = "/admin/clips/{id}/edit", name = "clips.edit")
    public String updateClip(@PathVariable int id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("clip",clipService.getClip(id));
        model.addAttribute("artists",artistService.getAllArtists());
        model.addAttribute("styles",styleService.getAllStyles());
        model.addAttribute("countries",countryService.getAllCountries());
        return "admin/clips/edit";
    }

    @PostMapping("/admin/clips/{id}/edit")
    public RedirectView updateClip(@PathVariable int id,
                                           @RequestParam String name,
                                           @RequestParam String url,
                                           @RequestParam String artist_id,
                                           @RequestParam String style_id,
                                           @RequestParam String country_id,
                                           Model model) {
        Clip clip = new Clip();
        List<Style> styles = clip.getClipStyles();
        styles.add(new Style(Integer.parseInt(style_id)));

        List<Artist> artists = clip.getClipArtists();
        artists.add(new Artist(Integer.parseInt(artist_id)));

        clip.setId(id);
        clip.setName(name);
        clip.setUrl(url);
        clip.setClipArtists(artists);
        clip.setClipStyles(styles);
        clip.setCountry(new Country(Integer.parseInt(country_id)));
        clipService.updateClip(clip);
        return new RedirectView("/admin/clips/" + clip.getId());
    }

    @PostMapping(value = "/admin/clips/{id}/comment", name = "clips.comment")
    public RedirectView commentClip(@PathVariable int id,
                                    @RequestParam String commentText,
                                    Model model) {
        Comment comment = new Comment();
        Clip clip = clipService.getClip(id);
        comment.setClip(clip);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        comment.setUser(user);
        comment.setComment(commentText);
        commentService.addComment(comment);
        model.addAttribute("user",user);
        model.addAttribute("clip",clip);
        return new RedirectView("/admin/clips/" + clip.getId());
    }

    @PostMapping(value = "/admin/clips/{id}/comment/delete", name = "clips.comment.delete")
    public RedirectView deleteCommentClip(@PathVariable int id,
                                          @RequestParam int commentId,
                                    Model model) {
        Comment comment = new Comment();
        comment.setId(commentId);
        Clip clip = clipService.getClip(id);
        comment.setClip(clip);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        comment.setUser(user);
        commentService.deleteComment(comment.getId());
        model.addAttribute("user",user);
        model.addAttribute("clip",clip);
        return new RedirectView("/admin/clips/" + clip.getId());
    }


    @PostMapping("/admin/clips/{id}/delete")
    public RedirectView  deleteClip(@PathVariable int id, Model model ){
        clipService.deleteClip(id);
        return new RedirectView("/admin/clips");
    }

}

