package com.worldmusic.WorldMusicSpring.services;

import com.worldmusic.WorldMusicSpring.model.Artist;
import com.worldmusic.WorldMusicSpring.model.Clip;
import com.worldmusic.WorldMusicSpring.model.Style;
import com.worldmusic.WorldMusicSpring.model.User;
import com.worldmusic.WorldMusicSpring.repositories.ClipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClipService {

    @Autowired
    private ClipRepository clipRepository;

    public List<Clip> getAllClips(){
        List<Clip> clips = new ArrayList<>();
        clipRepository.findAll()
                .forEach(clips::add);
        return clips;
    }
    public Clip getClip(int id){
        return clipRepository.findById(id).get();
    }

    public Clip getFirstStyle() { return clipRepository.findFirstByOrderByIdAsc(); }

    public List<Clip> getClipByUrl(String url){
        return clipRepository.findByUrl(url);
    }

    public long getCount(){
        return clipRepository.count();
    }

   /* public List<Clip> getClipArtist(int id){
        return clipRepository.findByArtistId(id);
    }*/

    public Clip addClip(Clip clip){
        clipRepository.save(clip);
        return clip;
    }
    public Clip updateClip(Clip clip){
        clipRepository.save(clip);
        return clip;
    }
    public boolean deleteClip(int id){
        clipRepository.deleteById(id);
        return true;
    }

    public long getCommentCount(int id){
        Clip clip = clipRepository.findById(id).get();
        return clip.getComments().size();
    }

    public long getLikesCount(int id){
        Clip clip = clipRepository.findById(id).get();
        return clip.getUserLikes().size();
    }

    public void vote(Clip clip, User user){
        List<User> usersLikes = clip.getUserLikes();
        usersLikes.add(user);
        clip.setUserLikes(usersLikes);
        int countLikes = clip.getCountLikes();
        clip.setCountLikes(countLikes + 1);
        clipRepository.save(clip);
    }
}
