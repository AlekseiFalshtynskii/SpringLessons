package ru.spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.dao.jdbc.GenreJdbcDao;
import ru.spring.model.Genre;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class GenreServiceImplTest {
    @MockBean
    private GenreJdbcDao daoMock;
    @Autowired
    private GenreService genreService;

    @Test
    public void saveTest() throws Exception {
        Genre expected = new Genre("");
        when(daoMock.insert(expected)).thenReturn(1);
        when(daoMock.update(expected)).thenReturn(1);
        Integer id = genreService.save(expected);
        assertEquals(1, id.intValue());
        verify(daoMock, times(1)).insert(expected);

        expected = new Genre(2, "");
        when(daoMock.insert(expected)).thenReturn(2);
        when(daoMock.update(expected)).thenReturn(2);
        id = genreService.save(expected);
        assertEquals(2, id.intValue());
        verify(daoMock, times(1)).update(expected);
    }

    @Test
    public void findByIdTest() throws Exception {
        Genre expected = new Genre(1, "");
        when(daoMock.findById(1)).thenReturn(expected);
        Genre genre = genreService.findById(1);
        assertEquals(expected, genre);
        verify(daoMock, times(1)).findById(1);
    }

    @Test
    public void findAllTest() throws Exception {
        List<Genre> expected = emptyList();
        when(daoMock.findAll()).thenReturn(expected);
        List<Genre> genres = genreService.findAll();
        assertEquals(expected, genres);
        verify(daoMock, times(1)).findAll();
    }

    @Test
    public void countTest() throws Exception {
        long expected = 1;
        when(daoMock.count()).thenReturn(expected);
        long count = genreService.count();
        assertEquals(expected, count);
        verify(daoMock, times(1)).count();
    }

    @Test
    public void deleteByIdTest() throws Exception {
        genreService.deleteById(1);
        verify(daoMock, times(1)).deleteById(1);
    }

    @Test
    public void deleteAllTest() throws Exception {
        genreService.deleteAll();
        verify(daoMock, times(1)).deleteAll();
    }
}