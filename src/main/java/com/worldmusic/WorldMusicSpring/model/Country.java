package com.worldmusic.WorldMusicSpring.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code_id;
    private String name;

    @OneToMany(mappedBy = "country")
    @JsonManagedReference
    @OrderBy("countLikes desc")
    private List<Clip> clips;

    @OneToMany(mappedBy = "country")
    private List<Artist> artists;

    public List<Clip> getTopClips(int count){
        if(clips.size() < count) {
            count = clips.size();
        }
        return new ArrayList<Clip>(clips.subList(0, count));
    }

    public Country(int id){
        this.id = id;
    }
}