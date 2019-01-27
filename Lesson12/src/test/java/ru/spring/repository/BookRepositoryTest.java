package ru.spring.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.model.Author;
import ru.spring.model.Book;
import ru.spring.model.Genre;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.spring.model.Author.authorOf;
import static ru.spring.model.Book.bookOf;
import static ru.spring.model.Genre.genreOf;

@RunWith(SpringRunner.class)
@DataMongoTest
public class BookRepositoryTest {
    private static final Author author1 = authorOf("1", "Уоллс", "Крейг");
    private static final Author author2 = authorOf("2", "Жемеров", "Дмитрий");
    private static final Author author3 = authorOf("3", "Исакова", "Светлана");
    private static final Genre genre1 = genreOf("1", "Программирование");
    private static final Book book1 = bookOf("1", "Spring в действии", "", asList(author1), asList(genre1));
    private static final Book book2 = bookOf("2", "Kotlin в действии", "", asList(author2, author3), asList(genre1));

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BookRepository repository;

    @Before
    public void init() {
        mongoTemplate.getDb().drop();
        mongoTemplate.insert(book1);
        mongoTemplate.insert(book2);
    }

    @Test
    public void insertTest() {
        Book book = bookOf("Node.js в действии", "", null, null);
        String id = repository.save(book).getId();
        assertNotEquals(null, id);
    }

    @Test
    public void updateTest() {
        Book expected = bookOf("1", "Spring в действии 2", "Описание", asList(author1), asList(genre1));
        repository.save(expected);
        Book actual = repository.findById("1").orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTest() {
        Book expected = book1;
        Book actual = repository.findById("1").orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllTest() {
        List<Book> expected = asList(book1, book2);
        List<Book> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countTest() {
        assertEquals(2, repository.count());
    }

    @Test
    public void deleteByIdTest() {
        List<Book> expected = asList(book2);
        repository.deleteById("1");
        List<Book> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteAllTest() {
        List<Book> expected = emptyList();
        repository.deleteAll();
        List<Book> actual = repository.findAll();
        assertEquals(expected, actual);
    }
}