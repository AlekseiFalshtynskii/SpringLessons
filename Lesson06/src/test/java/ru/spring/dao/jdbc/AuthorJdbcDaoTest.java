package ru.spring.dao.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.dao.AuthorDao;
import ru.spring.model.Author;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DirtiesContext(classMode = BEFORE_CLASS)
public class AuthorJdbcDaoTest {
    @Autowired
    private AuthorDao dao;

    @Test
    public void crud() throws Exception {
        Author expected = new Author("Фамилия", "Имя");
        Long id = dao.insert(expected);

        Author author = dao.findById(id);
        assertEquals(id, author.getId());
        assertEquals(expected.getLastName(), author.getLastName());
        assertEquals(expected.getFirstName(), author.getFirstName());
        assertEquals(1, dao.count());

        expected = new Author(id, "Новая фамилия", "Новое имя");
        dao.update(expected);
        author = dao.findById(id);
        assertEquals(id, author.getId());
        assertEquals(expected.getLastName(), author.getLastName());
        assertEquals(expected.getFirstName(), author.getFirstName());
        assertEquals(1, dao.count());

        List<Author> authors = dao.findAll();
        assertEquals(1, authors.size());
        assertEquals(expected.getLastName(), authors.get(0).getLastName());
        assertEquals(expected.getFirstName(), authors.get(0).getFirstName());

        dao.deleteById(id);
        authors = dao.findAll();
        assertEquals(0, authors.size());
        assertEquals(0, dao.count());

        dao.insert(expected);
        dao.insert(expected);
        authors = dao.findAll();
        assertEquals(2, authors.size());
        assertEquals(2, dao.count());

        dao.deleteAll();
        authors = dao.findAll();
        assertEquals(0, authors.size());
        assertEquals(0, dao.count());
    }
}