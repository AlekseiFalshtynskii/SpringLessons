package ru.spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.dao.BookDao;
import ru.spring.model.Book;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class BookServiceImplTest {
    @MockBean
    private BookDao daoMock;
    @Autowired
    private BookService bookService;

    @Test
    public void saveTest() throws Exception {
        Book expected = new Book("", "", emptyList(), emptyList());
        when(daoMock.insert(expected)).thenReturn(1L);
        when(daoMock.update(expected)).thenReturn(1L);
        Long id = bookService.save(expected);
        assertEquals(1, id.intValue());
        verify(daoMock, times(1)).insert(expected);

        expected = new Book(2L, "", "", emptyList(), emptyList());
        when(daoMock.insert(expected)).thenReturn(2L);
        when(daoMock.update(expected)).thenReturn(2L);
        id = bookService.save(expected);
        assertEquals(2, id.intValue());
        verify(daoMock, times(1)).update(expected);
    }

    @Test
    public void findByIdTest() throws Exception {
        Book expected = new Book(1L, "", "", emptyList(), emptyList());
        when(daoMock.findById(1L)).thenReturn(expected);
        Book book = bookService.findById(1L);
        assertEquals(expected, book);
        verify(daoMock, times(1)).findById(1L);
    }

    @Test
    public void findAllTest() throws Exception {
        List<Book> expected = emptyList();
        when(daoMock.findAll()).thenReturn(expected);
        List<Book> books = bookService.findAll();
        assertEquals(expected, books);
        verify(daoMock, times(1)).findAll();
    }

    @Test
    public void countTest() throws Exception {
        long expected = 1;
        when(daoMock.count()).thenReturn(expected);
        long count = bookService.count();
        assertEquals(expected, count);
        verify(daoMock, times(1)).count();
    }

    @Test
    public void deleteByIdTest() throws Exception {
        bookService.deleteById(1L);
        verify(daoMock, times(1)).deleteById(1L);
    }

    @Test
    public void deleteAllTest() throws Exception {
        bookService.deleteAll();
        verify(daoMock, times(1)).deleteAll();
    }
}