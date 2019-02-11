package ru.spring.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.model.Genre;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static ru.spring.model.Genre.genreOf;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext
@ComponentScan("ru.spring.dao")
public class GenreRepositoryTest {
    private static final Genre genre1 = genreOf(1L, "Программирование");

    @Autowired
    private GenreRepository dao;

    @Test
    public void insertTest() {
        Genre genre = genreOf("Фантастика");
        Long id = dao.save(genre).getId();
        assertEquals(2, id.longValue());
    }

    @Test
    public void updateTest() {
        Genre expected = genreOf(1L, "Фантастика");
        dao.save(expected);
        Genre actual = dao.findById(1L).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTest() {
        Genre expected = genre1;
        Genre actual = dao.findById(1L).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllTest() {
        List<Genre> expected = asList(genre1);
        List<Genre> actual = (List<Genre>) dao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countTest() {
        assertEquals(1, dao.count());
    }

    @Test
    @Sql("/delete-books.sql")
    public void deleteByIdTest() {
        List<Genre> expected = emptyList();
        dao.deleteById(1L);
        List<Genre> actual = (List<Genre>) dao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    @Sql("/delete-books.sql")
    public void deleteAllTest() {
        List<Genre> expected = emptyList();
        dao.deleteAll();
        List<Genre> actual = (List<Genre>) dao.findAll();
        assertEquals(expected, actual);
    }
}