package com.worldmusic.WorldMusicSpring.model;


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
    private Country country;
    @OneToMany(mappedBy = "artist")
    private List<Clip> clips;

    public Artist(int id){
        this.id = id;
    }

}
