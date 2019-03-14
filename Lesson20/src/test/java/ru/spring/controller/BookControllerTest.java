package ru.spring.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.spring.service.AuthorService;
import ru.spring.service.BookService;
import ru.spring.service.GenreService;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.spring.model.Author.authorOf;
import static ru.spring.model.Book.bookOf;
import static ru.spring.model.Genre.genreOf;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@WithMockUser(username = "admin")
public class BookControllerTest {

    @MockBean
    private BookService bookServiceMock;

    @MockBean
    private AuthorService authorServiceMock;

    @MockBean
    private GenreService genreServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addTest() throws Exception {
        when(authorServiceMock.findAll()).thenReturn(singletonList(authorOf()));
        when(genreServiceMock.findAll()).thenReturn(singletonList(genreOf()));
        mockMvc.perform(get("/books/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("book-edit"))
                .andExpect(model().attributeExists("book", "authors", "genres", "readonly", "module"))
                .andExpect(model().attribute("readonly", false))
                .andExpect(model().attribute("module", "books"));
    }

    @Test
    public void viewTest() throws Exception {
        when(bookServiceMock.findById(1L)).thenReturn(bookOf());
        mockMvc.perform(get("/books/{id}/view", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("book-edit"))
                .andExpect(model().attributeExists("book", "authors", "genres", "readonly", "module"))
                .andExpect(model().attribute("readonly", true))
                .andExpect(model().attribute("module", "books"));
    }

    @Test
    public void editTest() throws Exception {
        when(bookServiceMock.findById(1L)).thenReturn(bookOf());
        mockMvc.perform(get("/books/{id}/edit", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("book-edit"))
                .andExpect(model().attributeExists("book", "authors", "genres", "readonly", "module"))
                .andExpect(model().attribute("readonly", false))
                .andExpect(model().attribute("module", "books"));
    }

    @Test
    public void saveTest() throws Exception {
        mockMvc.perform(post("/books", bookOf()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteByIdTest() throws Exception {
        mockMvc.perform(delete("/books/{id}", 1))
                .andExpect(status().isFound());
    }

    @Test
    public void deleteAllTest() throws Exception {
        mockMvc.perform(delete("/books"))
                .andExpect(status().isFound());
    }
}