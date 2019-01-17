package ru.spring.dao.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.dao.BookDao;
import ru.spring.model.Author;
import ru.spring.model.Book;
import ru.spring.model.Genre;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static ru.spring.model.Author.authorOf;
import static ru.spring.model.Book.bookOf;
import static ru.spring.model.Genre.genreOf;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext
@ComponentScan("ru.spring.dao")
public class BookJpaRepositoryTest {
    private static final Author author1 = authorOf(1L, "Уоллс", "Крейг");
    private static final Author author2 = authorOf(2L, "Жемеров", "Дмитрий");
    private static final Author author3 = authorOf(3L, "Исакова", "Светлана");
    private static final Genre genre1 = genreOf(1L, "Программирование");
    private static final Book book1 = bookOf(1L, "Spring в действии", "", asList(author1), asList(genre1));
    private static final Book book2 = bookOf(2L, "Kotlin в действии", "", asList(author2, author3), asList(genre1));

    @Autowired
    private BookDao dao;

    @Test
    public void insertTest() {
        Book book = bookOf("Node.js в действии", "", null, null);
        Long id = dao.insert(book);
        assertEquals(3, id.longValue());
    }

    @Test
    public void updateTest() {
        Book expected = bookOf(1L, "Spring в действии 2", "Описание", asList(author1), asList(genre1));
        dao.update(expected);
        Book actual = dao.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTest() {
        Book expected = book1;
        Book actual = dao.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllTest() {
        List<Book> expected = asList(book1, book2);
        List<Book> actual = dao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countTest() {
        assertEquals(2, dao.count());
    }

    @Test
    public void deleteByIdTest() {
        List<Book> expected = asList(book2);
        dao.deleteById(1L);
        List<Book> actual = dao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteAllTest() {
        List<Book> expected = emptyList();
        dao.deleteAll();
        List<Book> actual = dao.findAll();
        assertEquals(expected, actual);
    }
}