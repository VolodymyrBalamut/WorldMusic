package com.worldmusic.WorldMusicSpring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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
    @ManyToOne
    private Artist artist;
    @ManyToOne
    private Style style;

    public Clip(String name, String url, int artistId, int styleId) {
        this.name = name;
        this.url = url;
        this.artist = new Artist(artistId,"","","");
        this.style = new Style(styleId,"","");
    }
}
