package com.worldmusic.WorldMusicSpring.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="artists")
public class Artist
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String biography;
   // private String country_code;
    @ManyToOne
    @JsonBackReference
    private Country country;

    @ManyToMany(mappedBy = "clipArtists")
    @JsonManagedReference
    private List<Clip> clips;

    public Artist(int id){
        this.id = id;
    }

}
