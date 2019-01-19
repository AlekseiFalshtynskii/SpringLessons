package ru.spring.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
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
@ComponentScan("ru.spring.repository")
public class BookRepositoryTest {
    private static final Author author1 = authorOf(1L, "Уоллс", "Крейг");
    private static final Author author2 = authorOf(2L, "Жемеров", "Дмитрий");
    private static final Author author3 = authorOf(3L, "Исакова", "Светлана");
    private static final Genre genre1 = genreOf(1L, "Программирование");
    private static final Book book1 = bookOf(1L, "Spring в действии", "", asList(author1), asList(genre1));
    private static final Book book2 = bookOf(2L, "Kotlin в действии", "", asList(author2, author3), asList(genre1));

    @Autowired
    private BookRepository repository;

    @Test
    public void insertTest() {
        Book book = bookOf("Node.js в действии", "", null, null);
        Long id = repository.save(book).getId();
        assertEquals(3, id.longValue());
    }

    @Test
    public void updateTest() {
        Book expected = bookOf(1L, "Spring в действии 2", "Описание", asList(author1), asList(genre1));
        repository.save(expected);
        Book actual = repository.findById(1L).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTest() {
        Book expected = book1;
        Book actual = repository.findById(1L).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllTest() {
        List<Book> expected = asList(book1, book2);
        List<Book> actual = (List<Book>) repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countTest() {
        assertEquals(2, repository.count());
    }

    @Test
    public void deleteByIdTest() {
        List<Book> expected = asList(book2);
        repository.deleteById(1L);
        List<Book> actual = (List<Book>) repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteAllTest() {
        List<Book> expected = emptyList();
        repository.deleteAll();
        List<Book> actual = (List<Book>) repository.findAll();
        assertEquals(expected, actual);
    }
}