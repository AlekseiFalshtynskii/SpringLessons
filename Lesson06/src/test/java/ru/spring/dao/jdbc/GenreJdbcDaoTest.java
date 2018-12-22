package ru.spring.dao.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.model.Genre;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class GenreJdbcDaoTest {
    @Autowired
    private GenreJdbcDao dao;

    @Test
    public void crud() throws Exception {
        Genre expected = new Genre("Фантастика");
        Integer id = dao.insert(expected);

        Genre genre = dao.findById(id);
        assertEquals(id, genre.getId());
        assertEquals(expected.getName(), genre.getName());
        assertEquals(1, dao.count());

        expected = new Genre(id, "Триллер");
        dao.update(expected);
        genre = dao.findById(id);
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

        dao.insert(expected);
        dao.insert(expected);
        genres = dao.findAll();
        assertEquals(2, genres.size());
        assertEquals(2, dao.count());

        dao.deleteAll();
        genres = dao.findAll();
        assertEquals(0, genres.size());
        assertEquals(0, dao.count());
    }
}