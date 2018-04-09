package com.worldmusic.WorldMusicSpring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Clip {
    private int id;
    private String name;
    private String url;
    private int performer_id;
    private int style_id;
}
