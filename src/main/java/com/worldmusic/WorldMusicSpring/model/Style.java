package com.worldmusic.WorldMusicSpring.model;

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
@Table(name="styles")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "clipStyles", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Clip> clips;

    public Style(int id) {
        this.id = id;
    }
    public Style(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
