package ru.spring.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.spring.service.GenreService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.spring.model.Genre.genreOf;

@RunWith(SpringRunner.class)
@WebMvcTest(GenreController.class)
@WithMockUser(username = "admin")
public class GenreControllerTest {

    @MockBean
    private GenreService genreServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addTest() throws Exception {
        mockMvc.perform(get("/genres/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("genre-edit"))
                .andExpect(model().attributeExists("genre", "readonly", "module"))
                .andExpect(model().attribute("readonly", false))
                .andExpect(model().attribute("module", "genres"));
    }

    @Test
    public void viewTest() throws Exception {
        when(genreServiceMock.findById(1L)).thenReturn(genreOf());
        mockMvc.perform(get("/genres/{id}/view", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("genre-edit"))
                .andExpect(model().attributeExists("genre", "readonly", "module"))
                .andExpect(model().attribute("readonly", true))
                .andExpect(model().attribute("module", "genres"));
    }

    @Test
    public void editTest() throws Exception {
        when(genreServiceMock.findById(1L)).thenReturn(genreOf());
        mockMvc.perform(get("/genres/{id}/edit", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("genre-edit"))
                .andExpect(model().attributeExists("genre", "readonly", "module"))
                .andExpect(model().attribute("readonly", false))
                .andExpect(model().attribute("module", "genres"));
    }

    @Test
    public void saveTest() throws Exception {
        mockMvc.perform(post("/genres", genreOf()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteByIdTest() throws Exception {
        mockMvc.perform(delete("/genres/{id}", 1))
                .andExpect(status().isFound());
    }

    @Test
    public void deleteAllTest() throws Exception {
        mockMvc.perform(delete("/genres"))
                .andExpect(status().isFound());
    }
}