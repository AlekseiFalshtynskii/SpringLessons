package ru.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.spring.service.AuthorService;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.spring.model.Author.authorOf;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @MockBean
    private AuthorService authorServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk());
    }

    @Test
    public void getByIdTest() throws Exception {
        mockMvc.perform(get("/authors/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void saveTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String body = ow.writeValueAsString(authorOf());
        mockMvc.perform(post("/authors").contentType(APPLICATION_JSON_UTF8).content(body))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteByIdTest() throws Exception {
        mockMvc.perform(delete("/authors/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllTest() throws Exception {
        mockMvc.perform(delete("/authors"))
                .andExpect(status().isOk());
    }
}