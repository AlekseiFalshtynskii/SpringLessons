package ru.spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.dao.jdbc.AuthorJdbcDao;
import ru.spring.model.Author;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class AuthorServiceImplTest {
    @MockBean
    private AuthorJdbcDao daoMock;
    @Autowired
    private AuthorService authorService;

    @Test
    public void saveTest() throws Exception {
        Author expected = new Author("", "");
        when(daoMock.insert(expected)).thenReturn(1);
        when(daoMock.update(expected)).thenReturn(1);
        Integer id = authorService.save(expected);
        assertEquals(1, id.intValue());
        verify(daoMock, times(1)).insert(expected);

        expected = new Author(2, "", "");
        when(daoMock.insert(expected)).thenReturn(2);
        when(daoMock.update(expected)).thenReturn(2);
        id = authorService.save(expected);
        assertEquals(2, id.intValue());
        verify(daoMock, times(1)).update(expected);
    }

    @Test
    public void findByIdTest() throws Exception {
        Author expected = new Author(1, "", "");
        when(daoMock.findById(1)).thenReturn(expected);
        Author author = authorService.findById(1);
        assertEquals(expected, author);
        verify(daoMock, times(1)).findById(1);
    }

    @Test
    public void findAllTest() throws Exception {
        List<Author> expected = emptyList();
        when(daoMock.findAll()).thenReturn(expected);
        List<Author> authors = authorService.findAll();
        assertEquals(expected, authors);
        verify(daoMock, times(1)).findAll();
    }

    @Test
    public void countTest() throws Exception {
        long expected = 1;
        when(daoMock.count()).thenReturn(expected);
        long count = authorService.count();
        assertEquals(expected, count);
        verify(daoMock, times(1)).count();
    }

    @Test
    public void deleteByIdTest() throws Exception {
        authorService.deleteById(1);
        verify(daoMock, times(1)).deleteById(1);
    }

    @Test
    public void deleteAllTest() throws Exception {
        authorService.deleteAll();
        verify(daoMock, times(1)).deleteAll();
    }
}