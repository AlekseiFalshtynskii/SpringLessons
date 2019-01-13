package ru.spring.dao.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.dao.AuthorDao;
import ru.spring.model.Author;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.spring.model.Author.authorOf;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("ru.spring")
public class AuthorJpaRepositoryTest {
    @Autowired
    private AuthorDao dao;

    @Test
    public void crud() throws Exception {
        Author expected = authorOf("Фамилия", "Имя");
        Long id = dao.insert(expected);

        Author author = dao.findById(id);
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

        dao.insert(authorOf("Иванов", "Иван"));
        dao.insert(authorOf("Петров", "Петр"));
        authors = dao.findAll();
        assertEquals(2, authors.size());
        assertEquals(2, dao.count());

        dao.deleteAll();
        authors = dao.findAll();
        assertEquals(0, authors.size());
        assertEquals(0, dao.count());
    }
}