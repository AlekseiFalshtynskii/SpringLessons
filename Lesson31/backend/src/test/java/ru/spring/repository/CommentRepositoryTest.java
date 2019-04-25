package ru.spring.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.model.Author;
import ru.spring.model.Book;
import ru.spring.model.Comment;
import ru.spring.model.Genre;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static ru.spring.model.Author.authorOf;
import static ru.spring.model.Book.bookOf;
import static ru.spring.model.Comment.commentOf;
import static ru.spring.model.Genre.genreOf;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext
@ComponentScan("ru.spring.repository")
public class CommentRepositoryTest {
    private static final Author author1 = authorOf(1L, "Уоллс", "Крейг");
    private static final Author author2 = authorOf(2L, "Жемеров", "Дмитрий");
    private static final Author author3 = authorOf(3L, "Исакова", "Светлана");
    private static final Genre genre1 = genreOf(1L, "Программирование");
    private static final Book book1 = bookOf(1L, "Spring в действии", "", asList(author1), asList(genre1));
    private static final Book book2 = bookOf(2L, "Kotlin в действии", "", asList(author2, author3), asList(genre1));
    private static final Comment comment1 = commentOf(1L, "Отличная книга", book1);
    private static final Comment comment2 = commentOf(2L, "Нормальная книга", book1);
    private static final Comment comment3 = commentOf(3L, "Не очень книга", book2);

    @Autowired
    private CommentRepository repository;

    @Test
    public void insertTest() {
        Comment comment = commentOf("Отличная книга!", bookOf(1L, "", "", null, null));
        Long id = repository.save(comment).getId();
        assertEquals(4, id.longValue());
    }

    @Test
    public void updateTest() {
        Comment expected = comment1;
        repository.save(expected);
        Comment actual = repository.findById(1L).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTest() {
        Comment expected = comment2;
        Comment actual = repository.findById(2L).orElse(null);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getMessage(), actual.getMessage());
        assertEquals(expected.getBook().getId(), actual.getBook().getId());
    }

    @Test
    public void findAllTest() {
        List<Comment> expected = asList(comment1, comment2, comment3);
        List<Comment> actual = (List<Comment>) repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countTest() {
        assertEquals(3, repository.count());
    }

    @Test
    public void deleteByIdTest() {
        List<Comment> expected = asList(comment1, comment3);
        repository.deleteById(2L);
        List<Comment> actual = (List<Comment>) repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteAllTest() {
        List<Comment> expected = emptyList();
        repository.deleteAll();
        List<Comment> actual = (List<Comment>) repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findByBookIdTest() {
        List<Comment> expected = asList(comment1, comment2);
        List<Comment> actual = repository.findByBookId(1L);
        assertEquals(expected, actual);
    }
}