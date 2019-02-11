package ru.spring.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.spring.service.AuthorService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.spring.model.Author.authorOf;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @MockBean
    private AuthorService authorServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addTest() throws Exception {
        mockMvc.perform(get("/authors/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("author-edit"));
    }

    @Test
    public void viewTest() throws Exception {
        when(authorServiceMock.findById(1L)).thenReturn(authorOf());
        mockMvc.perform(get("/authors/{id}/view", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("author-edit"))
                .andExpect(model().attributeExists("author", "readonly"))
                .andExpect(model().attribute("readonly", true));
    }

    @Test
    public void editTest() throws Exception {
        when(authorServiceMock.findById(1L)).thenReturn(authorOf());
        mockMvc.perform(get("/authors/{id}/edit", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("author-edit"))
                .andExpect(model().attributeExists("author", "readonly"))
                .andExpect(model().attribute("readonly", false));
    }

    @Test
    public void saveTest() throws Exception {
        mockMvc.perform(post("/authors", authorOf()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteByIdTest() throws Exception {
        mockMvc.perform(delete("/authors/{id}", 1))
                .andExpect(status().isFound());
    }

    @Test
    public void deleteAllTest() throws Exception {
        mockMvc.perform(delete("/authors"))
                .andExpect(status().isFound());
    }
}