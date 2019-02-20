package ru.spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.model.Author;
import ru.spring.repository.AuthorRepository;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static ru.spring.model.Author.authorOf;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class AuthorServiceImplTest {
    @MockBean
    private AuthorRepository repositoryMock;
    @Autowired
    private AuthorService authorService;

    @Test
    public void saveTest() throws Exception {
        Author author1 = authorOf("", "");
        Author author2 = authorOf(1L, "", "");
        when(repositoryMock.save(author1)).thenReturn(author2);
        author1 = authorService.save(author1);
        assertEquals(1, author1.getId().intValue());
        verify(repositoryMock, times(1)).save(author1);
    }

    @Test
    public void findByIdTest() throws Exception {
        Author expected = authorOf(1L, "", "");
        when(repositoryMock.findById(1L)).thenReturn(of(expected));
        Author author = authorService.findById(1L);
        assertEquals(expected, author);
        verify(repositoryMock, times(1)).findById(1L);
    }

    @Test
    public void findAllTest() throws Exception {
        List<Author> expected = emptyList();
        when(repositoryMock.findAll()).thenReturn(expected);
        List<Author> authors = authorService.findAll();
        assertEquals(expected, authors);
        verify(repositoryMock, times(1)).findAll();
    }

    @Test
    public void countTest() throws Exception {
        long expected = 1;
        when(repositoryMock.count()).thenReturn(expected);
        long count = authorService.count();
        assertEquals(expected, count);
        verify(repositoryMock, times(1)).count();
    }

    @Test
    public void deleteByIdTest() throws Exception {
        authorService.deleteById(1L);
        verify(repositoryMock, times(1)).deleteById(1L);
    }

    @Test
    public void deleteAllTest() throws Exception {
        authorService.deleteAll();
        verify(repositoryMock, times(1)).deleteAll();
    }
}