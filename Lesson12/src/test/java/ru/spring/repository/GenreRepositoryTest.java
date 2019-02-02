package ru.spring.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.model.Genre;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.spring.model.Genre.genreOf;

@RunWith(SpringRunner.class)
@DataMongoTest
public class GenreRepositoryTest {
    private static final Genre genre1 = genreOf("1", "Программирование");

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private GenreRepository dao;

    @Before
    public void init() {
        mongoTemplate.getDb().drop();
        mongoTemplate.insert(genre1);
    }

    @Test
    public void insertTest() {
        Genre genre = genreOf("Фантастика");
        String id = dao.save(genre).getId();
        assertNotEquals(null, id);
    }

    @Test
    public void updateTest() {
        Genre expected = genreOf("1", "Фантастика");
        dao.save(expected);
        Genre actual = dao.findById("1").orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTest() {
        Genre expected = genre1;
        Genre actual = dao.findById("1").orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllTest() {
        List<Genre> expected = asList(genre1);
        List<Genre> actual = dao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countTest() {
        assertEquals(1, dao.count());
    }

    @Test
    public void deleteByIdTest() {
        List<Genre> expected = emptyList();
        dao.deleteById("1");
        List<Genre> actual = dao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteAllTest() {
        List<Genre> expected = emptyList();
        dao.deleteAll();
        List<Genre> actual = dao.findAll();
        assertEquals(expected, actual);
    }
}