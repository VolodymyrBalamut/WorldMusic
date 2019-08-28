package com.worldmusic.WorldMusicSpring.controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldmusic.WorldMusicSpring.model.Artist;
import com.worldmusic.WorldMusicSpring.model.Clip;
import com.worldmusic.WorldMusicSpring.model.Style;
import com.worldmusic.WorldMusicSpring.services.ClipService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.nio.charset.Charset;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClipControllerAPITest {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllClips() throws Exception {
        mockMvc.perform(get("/api/clips").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetClip() throws Exception {
        mockMvc.perform(get("/api/clips/50").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(50)));
    }

    @Test
    public void testCreateClip() throws Exception {
        Clip clip = Clip.builder()
                .name("Test").url("fdfd")
                .build();
        clip.setArtist(new Artist(1,"","",null,null));
        clip.setStyle(new Style(3));
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(clip);

        this.mockMvc.perform(post("/api/clips")
                .contentType(contentType)
                .content(jsonInString))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateClip() throws Exception {
        Clip clip = Clip.builder().id(50)
                .name("TestUpdate").url("fdfd")
                .build();
        clip.setArtist(new Artist(3,"","",null,null));
        clip.setStyle(new Style(1));
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(clip);

        this.mockMvc.perform(put("/api/clips/50")
                .contentType(contentType)
                .content(jsonInString))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteClip() throws Exception {
        ClipService clipService = new ClipService();
        int id = 69;
        mockMvc.perform(delete("/api/clips/"+id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
