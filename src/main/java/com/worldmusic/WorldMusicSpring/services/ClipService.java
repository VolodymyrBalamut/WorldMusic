package com.worldmusic.WorldMusicSpring.services;

import com.worldmusic.WorldMusicSpring.model.Clip;
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
    public Optional<Clip> getClip(int id){
            return clipRepository.findById(id);
    }


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
}
