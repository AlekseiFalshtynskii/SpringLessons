package ru.spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@SpringBootTest
@DirtiesContext
public class GenreServiceImplTest {
    @MockBean
    private GenreRepository repositoryMock;
    @Autowired
    private GenreService genreService;

    @Test
    public void saveTest() throws Exception {
        Genre genre1 = genreOf("");
        Genre genre2 = genreOf(1L, "");
        when(repositoryMock.save(genre1)).thenReturn(genre2);
        Genre genre3 = genreService.save(genre1);
        assertEquals(1, genre3.getId().intValue());
        verify(repositoryMock, times(1)).save(genre1);
    }

    @Test
    public void findByIdTest() throws Exception {
        Genre expected = genreOf(1L, "");
        when(repositoryMock.findById(1L)).thenReturn(of(expected));
        Genre genre = genreService.findById(1L);
        assertEquals(expected, genre);
        verify(repositoryMock, times(1)).findById(1L);
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
        genreService.deleteById(1L);
        verify(repositoryMock, times(1)).deleteById(1L);
    }

    @Test
    public void deleteAllTest() throws Exception {
        genreService.deleteAll();
        verify(repositoryMock, times(1)).deleteAll();
    }
}