package ru.spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.model.Genre;
import ru.spring.repository.GenreRepository;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static ru.spring.model.Genre.genreOf;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DirtiesContext
public class GenreServiceImplTest {
    @MockBean
    private GenreRepository repositoryMock;
    @Autowired
    private GenreService genreService;

    @Test
    public void saveTest() throws Exception {
        Genre genre1 = genreOf("");
        Genre genre2 = genreOf("1", "");
        when(repositoryMock.save(genre1)).thenReturn(genre2);
        String id = genreService.save(genre1);
        assertEquals("1", id);
        verify(repositoryMock, times(1)).save(genre1);
    }

    @Test
    public void findByIdTest() throws Exception {
        Genre expected = genreOf("1", "");
        when(repositoryMock.findById("1")).thenReturn(of(expected));
        Genre genre = genreService.findById("1");
        assertEquals(expected, genre);
        verify(repositoryMock, times(1)).findById("1");
    }

    @Test
    public void findAllTest() throws Exception {
        List<Genre> expected = emptyList();
        when(repositoryMock.findAll()).thenReturn(expected);
        List<Genre> genres = genreService.findAll();
        assertEquals(expected, genres);
        verify(repositoryMock, times(1)).findAll();
    }

    @Test
    public void countTest() throws Exception {
        long expected = 1;
        when(repositoryMock.count()).thenReturn(expected);
        long count = genreService.count();
        assertEquals(expected, count);
        verify(repositoryMock, times(1)).count();
    }

    @Test
    public void deleteByIdTest() throws Exception {
        genreService.deleteById("1");
        verify(repositoryMock, times(1)).deleteById("1");
    }

    @Test
    public void deleteAllTest() throws Exception {
        genreService.deleteAll();
        verify(repositoryMock, times(1)).deleteAll();
    }
}