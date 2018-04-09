package com.worldmusic.WorldMusicSpring.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Artist
{
    private int id;
    private String name;
    private String biography;
    private String country_code;
}
