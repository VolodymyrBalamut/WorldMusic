package com.worldmusic.WorldMusicSpring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    private String country_code;
    private String country_name;
}
