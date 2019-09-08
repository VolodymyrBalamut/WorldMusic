package com.worldmusic.WorldMusicSpring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="clips")
public class Clip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String url;
    @Column(name="count_likes")
    private int countLikes;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JsonBackReference
    @JoinTable(
            name = "clip_artist",
            joinColumns = @JoinColumn(name = "clip_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<Artist> clipArtists;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JsonBackReference
    @JoinTable(
            name = "clip_style",
            joinColumns = @JoinColumn(name = "clip_id"),
            inverseJoinColumns = @JoinColumn(name = "style_id"))
    private List<Style> clipStyles;

    @ManyToOne
    @JsonBackReference
    private Country country;


    @OneToMany(mappedBy = "clip")
    @JsonManagedReference
    private List<Comment> comments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(name = "voting",
              joinColumns = @JoinColumn(name = "clip_id"),
              inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> userLikes;

    public Clip(int id){
        this.id = id;
    }
    /*public Clip(String name, String url, int artistId, int styleId) {
        this.name = name;
        this.url = url;
        this.artist = new Artist(artistId,"","","");
        this.style = new Style(styleId,"","");
    }*/

    public Artist getFirstArtist(){
        return this.clipArtists.get(0);
    }

    public Style getFirstStyle(){
        return this.clipStyles.get(0);
    }
}
