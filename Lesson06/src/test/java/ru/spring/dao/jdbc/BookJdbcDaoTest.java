package ru.spring.dao.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.dao.BookDao;
import ru.spring.model.Author;
import ru.spring.model.Book;
import ru.spring.model.Genre;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DirtiesContext(classMode = BEFORE_CLASS)
public class BookJdbcDaoTest {
    @Autowired
    private BookDao dao;

    @Test
    public void crud() throws Exception {
        List<Author> authors = Arrays.asList(
                new Author("Фамилия1", "Имя1"),
                new Author("Фамилия2", "Имя2")
        );
        List<Genre> genres = Arrays.asList(
                new Genre("Фантастика"),
                new Genre("Фэнтези")
        );
        Book expected = new Book("Название", "Описание", authors, genres);
        Long id = dao.insert(expected);

        Book book = dao.findById(id);
        assertEquals(id, book.getId());
        assertEquals(expected.getName(), book.getName());
        assertEquals(expected.getDescription(), book.getDescription());
        assertEquals(2, book.getAuthors().size());
        assertEquals(2, book.getGenres().size());
        assertEquals(expected.getAuthors().get(0).getLastName(), book.getAuthors().get(0).getLastName());
        assertEquals(expected.getAuthors().get(0).getFirstName(), book.getAuthors().get(0).getFirstName());
        assertEquals(expected.getAuthors().get(1).getLastName(), book.getAuthors().get(1).getLastName());
        assertEquals(expected.getAuthors().get(1).getFirstName(), book.getAuthors().get(1).getFirstName());
        assertEquals(expected.getGenres().get(0).getName(), book.getGenres().get(0).getName());
        assertEquals(expected.getGenres().get(1).getName(), book.getGenres().get(1).getName());
        assertEquals(1, dao.count());

        List<Book> books = dao.findAll();
        assertEquals(1, books.size());

        dao.deleteById(id);
        books = dao.findAll();
        assertEquals(0, books.size());
        assertEquals(0, dao.count());

        dao.insert(expected);
        dao.insert(expected);
        books = dao.findAll();
        assertEquals(2, books.size());
        assertEquals(2, dao.count());

        dao.deleteAll();
        books = dao.findAll();
        assertEquals(0, books.size());
        assertEquals(0, dao.count());
    }
}