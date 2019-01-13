package ru.spring.dao.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.dao.BookDao;
import ru.spring.model.Author;
import ru.spring.model.Book;
import ru.spring.model.Genre;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static ru.spring.model.Author.authorOf;
import static ru.spring.model.Book.bookOf;
import static ru.spring.model.Genre.genreOf;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("ru.spring")
public class BookJpaRepositoryTest {
    @Autowired
    private BookDao dao;

    @Test
    public void crud() throws Exception {
        List<Author> authors = asList(
                authorOf("Фамилия1", "Имя1"),
                authorOf("Фамилия2", "Имя2")
        );
        List<Genre> genres = asList(
                genreOf("Фантастика"),
                genreOf("Фэнтези")
        );
        Book expected = bookOf("Название", "Описание", authors, genres, null);
        Long id = dao.insert(expected);

        Book book = dao.findById(id);
        assertEquals(id, book.getId());
        assertEquals(expected.getName(), book.getName());
        assertEquals(expected.getDescription(), book.getDescription());
        assertEquals(2, book.getAuthors().size());
        assertEquals(2, book.getGenres().size());
        assertEquals(expected.getAuthors().get(0).getLastName(), book.getAuthors().get(0).getLastName());
        assertEquals(expected.getAuthors().get(0).getFirstName(), book.getAuthors().get(0).getFirstName());
        assertEquals(expected.getAuthors().get(1).getLastName(), book.getAuthors().get(1).getLastName());
        assertEquals(expected.getAuthors().get(1).getFirstName(), book.getAuthors().get(1).getFirstName());
        assertEquals(expected.getGenres().get(0).getName(), book.getGenres().get(0).getName());
        assertEquals(expected.getGenres().get(1).getName(), book.getGenres().get(1).getName());
        assertEquals(1, dao.count());

        List<Book> books = dao.findAll();
        assertEquals(1, books.size());

        dao.deleteById(id);
        books = dao.findAll();
        assertEquals(0, books.size());
        assertEquals(0, dao.count());

        dao.insert(bookOf("Название", "Описание", null, null, null));
        dao.insert(bookOf("Название", "Описание", null, null, null));
        books = dao.findAll();
        assertEquals(2, books.size());
        assertEquals(2, dao.count());

        dao.deleteAll();
        books = dao.findAll();
        assertEquals(0, books.size());
        assertEquals(0, dao.count());
    }
}