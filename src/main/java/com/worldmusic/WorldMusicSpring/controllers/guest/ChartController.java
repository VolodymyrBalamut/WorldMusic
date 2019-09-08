package com.worldmusic.WorldMusicSpring.controllers.guest;

import com.worldmusic.WorldMusicSpring.model.*;
import com.worldmusic.WorldMusicSpring.services.ClipService;
import com.worldmusic.WorldMusicSpring.services.CommentService;
import com.worldmusic.WorldMusicSpring.services.CountryService;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChartController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClipService clipService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/charts")
    public String getCharts(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("countries",countryService.getCountriesWithClips());
        return "guest/charts";
    }

    @GetMapping("/charts/country/{id}")
    public String getChartCoutnry(@PathVariable int id, Model model){
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //User user = userService.findUserByEmail(auth.getName());
        //model.addAttribute("user",user);
        //model.addAttribute("clip",clipService.getClip(id));
        //model.addAttribute("commentCount", clipService.getCommentCount(id));
        //model.addAttribute("likesCount",clipService.getLikesCount(id));
        model.addAttribute("country",countryService.getCountry(id));
        return "guest/countrychart";
    }

    @GetMapping("/charts/clip/{id}")
    public String getChartClip(@PathVariable int id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("clip",clipService.getClip(id));
        model.addAttribute("commentCount", clipService.getCommentCount(id));
        model.addAttribute("likesCount",clipService.getLikesCount(id));
        return "guest/clip";
    }

    @GetMapping("/charts/clip/{id}/artist")
    public String getChartClipArtist(@PathVariable int id, Model model){
        Clip clip = clipService.getClip(id);
        List<Artist> artists = clip.getClipArtists();
        model.addAttribute("artist",artists.get(0));
        return "guest/artist";
    }

    @GetMapping("/charts/clip/{id}/style")
    public String getChartClipStyle(@PathVariable int id, Model model){
        Clip clip = clipService.getClip(id);
        //Style style = clip.getStyle();
        List<Style> styles = new ArrayList<>(clip.getClipStyles());
        Style style = styles.get(0);
        model.addAttribute("style",style);
        return "guest/style";
    }

    @PostMapping(value = "/charts/clip/{id}/comment", name = "charts.clip.comment")
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
        return new RedirectView("/charts/clip/" + clip.getId());
    }

    @PostMapping(value = "/charts/clip/{id}/vote", name = "charts.clip.vote")
    public RedirectView voteClip(@PathVariable int id,
                                    Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Clip clip = clipService.getClip(id);
        clipService.vote(clip,user);
        model.addAttribute("user",user);
        model.addAttribute("clip",clip);
        return new RedirectView("/charts/clip/" + clip.getId());
    }
}
