package ru.spring.dao.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.dao.AuthorDao;
import ru.spring.model.Author;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static ru.spring.model.Author.authorOf;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext
@ComponentScan("ru.spring.dao")
public class AuthorJpaRepositoryTest {
    private static final Author author1 = authorOf(1L, "Уоллс", "Крейг");
    private static final Author author2 = authorOf(2L, "Жемеров", "Дмитрий");
    private static final Author author3 = authorOf(3L, "Исакова", "Светлана");

    @Autowired
    private AuthorDao dao;

    @Test
    public void insertTest() {
        Author author = authorOf("Иванов", "Иван");
        Long id = dao.insert(author);
        assertEquals(4, id.longValue());
    }

    @Test
    public void updateTest() {
        Author expected = authorOf(1L, "Кантелон", "Майк");
        dao.update(expected);
        Author actual = dao.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTest() {
        Author expected = author1;
        Author actual = dao.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllTest() {
        List<Author> expected = asList(author1, author2, author3);
        List<Author> actual = dao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countTest() {
        assertEquals(3, dao.count());
    }

    @Test
    public void deleteByIdTest() {
        List<Author> expected = asList(author1, author2);
        dao.deleteById(3L);
        List<Author> actual = dao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteAllTest() {
        List<Author> expected = emptyList();
        dao.deleteAll();
        List<Author> actual = dao.findAll();
        assertEquals(expected, actual);
    }
}