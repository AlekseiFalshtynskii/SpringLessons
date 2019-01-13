package ru.spring.dao.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.dao.GenreDao;
import ru.spring.model.Genre;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.spring.model.Genre.genreOf;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("ru.spring")
public class GenreJpaRepositoryTest {
    @Autowired
    private GenreDao dao;

    @Test
    public void crud() throws Exception {
        Genre expected = genreOf("Фантастика");
        Long id = dao.insert(expected);

        Genre genre = dao.findById(id);
        assertEquals(id, genre.getId());
        assertEquals(expected.getName(), genre.getName());
        assertEquals(1, dao.count());

        List<Genre> genres = dao.findAll();
        assertEquals(1, genres.size());
        assertEquals(expected.getName(), genres.get(0).getName());

        dao.deleteById(id);
        genres = dao.findAll();
        assertEquals(0, genres.size());
        assertEquals(0, dao.count());

        dao.insert(genreOf("Фантастика"));
        dao.insert(genreOf("Триллер"));
        genres = dao.findAll();
        assertEquals(2, genres.size());
        assertEquals(2, dao.count());

        dao.deleteAll();
        genres = dao.findAll();
        assertEquals(0, genres.size());
        assertEquals(0, dao.count());
    }
}