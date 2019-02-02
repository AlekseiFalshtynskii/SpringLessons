package ru.spring.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.model.Author;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.spring.model.Author.authorOf;

@RunWith(SpringRunner.class)
@DataMongoTest
public class AuthorRepositoryTest {
    private static final Author author1 = authorOf("1", "Уоллс", "Крейг");
    private static final Author author2 = authorOf("2", "Жемеров", "Дмитрий");
    private static final Author author3 = authorOf("3", "Исакова", "Светлана");

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private AuthorRepository repository;

    @Before
    public void init() {
        mongoTemplate.getDb().drop();
        mongoTemplate.insert(author1);
        mongoTemplate.insert(author2);
        mongoTemplate.insert(author3);
    }

    @Test
    public void insertTest() {
        Author author = authorOf("Иванов", "Иван");
        String id = repository.save(author).getId();
        assertNotEquals(null, id);
    }

    @Test
    public void updateTest() {
        Author expected = authorOf("1", "Кантелон", "Майк");
        repository.save(expected);
        Author actual = repository.findById("1").orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTest() {
        Author expected = author1;
        Author actual = repository.findById("1").orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllTest() {
        List<Author> expected = asList(author1, author2, author3);
        List<Author> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countTest() {
        assertEquals(3, repository.count());
    }

    @Test
    public void deleteByIdTest() {
        List<Author> expected = asList(author1, author2);
        repository.deleteById("3");
        List<Author> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteAllTest() {
        List<Author> expected = emptyList();
        repository.deleteAll();
        List<Author> actual = repository.findAll();
        assertEquals(expected, actual);
    }
}