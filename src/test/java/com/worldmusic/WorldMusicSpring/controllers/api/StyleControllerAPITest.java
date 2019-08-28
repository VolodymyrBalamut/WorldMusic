package com.worldmusic.WorldMusicSpring.controllers.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldmusic.WorldMusicSpring.model.Style;
import com.worldmusic.WorldMusicSpring.services.StyleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StyleControllerAPITest {
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
    public void testGetAllStyles() throws Exception {
        mockMvc.perform(get("/api/styles").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetStyle() throws Exception {
        mockMvc.perform(get("/api/styles/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    public void testGetTopStyle() throws Exception {
        StyleService styleService = new StyleService();
        Style style = styleService.getTopStyle();
        int id = style.getId();
        mockMvc.perform(get("/api/styles/top").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(id)));
    }
    @Test
    public void testCreateStyle() throws Exception {
        Style style = Style.builder()
                .name("POP").description("It is POP style")
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(style);

        this.mockMvc.perform(post("/api/styles")
                .contentType(contentType)
                .content(jsonInString))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateStyle() throws Exception {
        Style topStyle = new StyleService().getTopStyle();

        Style style = Style.builder().id(topStyle.getId())
                .name("POP2").description("It is POP2 style")
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(style);

        this.mockMvc.perform(put("/api/clips/"+ topStyle.getId())
                .contentType(contentType)
                .content(jsonInString))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteStyle() throws Exception {
        Style topStyle = new StyleService().getTopStyle();
        mockMvc.perform(delete("/api/styles/"+topStyle.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
