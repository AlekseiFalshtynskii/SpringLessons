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
import ru.spring.model.Book;
import ru.spring.repository.BookRepository;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static ru.spring.model.Book.bookOf;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DirtiesContext
public class BookServiceImplTest {
    @MockBean
    private BookRepository repositoryMock;
    @Autowired
    private BookService bookService;

    @Test
    public void saveTest() throws Exception {
        Book book1 = bookOf("", "", emptyList(), emptyList());
        Book book2 = bookOf(1L, "", "", emptyList(), emptyList());
        when(repositoryMock.save(book1)).thenReturn(book2);
        Long id = bookService.save(book1);
        assertEquals(1, id.intValue());
        verify(repositoryMock, times(1)).save(book1);
    }

    @Test
    public void findByIdTest() throws Exception {
        Book expected = bookOf(1L, "", "", emptyList(), emptyList());
        when(repositoryMock.findById(1L)).thenReturn(of(expected));
        Book book = bookService.findById(1L);
        assertEquals(expected, book);
        verify(repositoryMock, times(1)).findById(1L);
    }

    @Test
    public void findAllTest() throws Exception {
        List<Book> expected = emptyList();
        when(repositoryMock.findAll()).thenReturn(expected);
        List<Book> books = bookService.findAll();
        assertEquals(expected, books);
        verify(repositoryMock, times(1)).findAll();
    }

    @Test
    public void countTest() throws Exception {
        long expected = 1;
        when(repositoryMock.count()).thenReturn(expected);
        long count = bookService.count();
        assertEquals(expected, count);
        verify(repositoryMock, times(1)).count();
    }

    @Test
    public void deleteByIdTest() throws Exception {
        bookService.deleteById(1L);
        verify(repositoryMock, times(1)).deleteById(1L);
    }

    @Test
    public void deleteAllTest() throws Exception {
        bookService.deleteAll();
        verify(repositoryMock, times(1)).deleteAll();
    }
}