package ru.spring.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.model.Author;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static ru.spring.model.Author.authorOf;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext
@ComponentScan("ru.spring.repository")
public class AuthorRepositoryTest {
    private static final Author author1 = authorOf(1L, "Уоллс", "Крейг");
    private static final Author author2 = authorOf(2L, "Жемеров", "Дмитрий");
    private static final Author author3 = authorOf(3L, "Исакова", "Светлана");

    @Autowired
    private AuthorRepository repository;

    @Test
    public void insertTest() {
        Author author = authorOf("Иванов", "Иван");
        Long id = repository.save(author).getId();
        assertEquals(4, id.longValue());
    }

    @Test
    public void updateTest() {
        Author expected = authorOf(1L, "Кантелон", "Майк");
        repository.save(expected);
        Author actual = repository.findById(1L).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTest() {
        Author expected = author1;
        Author actual = repository.findById(1L).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllTest() {
        List<Author> expected = asList(author1, author2, author3);
        List<Author> actual = (List<Author>) repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countTest() {
        assertEquals(3, repository.count());
    }

    @Test
    @Sql("/delete-books.sql")
    public void deleteByIdWithoutBooksTest() {
        List<Author> expected = asList(author1, author2);
        repository.deleteById(3L);
        List<Author> actual = (List<Author>) repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    @Sql("/delete-books.sql")
    public void deleteAllTest() {
        List<Author> expected = emptyList();
        repository.deleteAll();
        List<Author> actual = (List<Author>) repository.findAll();
        assertEquals(expected, actual);
    }
}