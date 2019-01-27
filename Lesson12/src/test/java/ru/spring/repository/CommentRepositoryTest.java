package ru.spring.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.model.Author;
import ru.spring.model.Book;
import ru.spring.model.Comment;
import ru.spring.model.Genre;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.spring.model.Author.authorOf;
import static ru.spring.model.Book.bookOf;
import static ru.spring.model.Comment.commentOf;
import static ru.spring.model.Genre.genreOf;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CommentRepositoryTest {
    private static final Author author1 = authorOf("1", "Уоллс", "Крейг");
    private static final Author author2 = authorOf("2", "Жемеров", "Дмитрий");
    private static final Author author3 = authorOf("3", "Исакова", "Светлана");
    private static final Genre genre1 = genreOf("1", "Программирование");
    private static final Book book1 = bookOf("1", "Spring в действии", "", asList(author1), asList(genre1));
    private static final Book book2 = bookOf("2", "Kotlin в действии", "", asList(author2, author3), asList(genre1));
    private static final Comment comment1 = commentOf("1", "Отличная книга", book1);
    private static final Comment comment2 = commentOf("2", "Нормальная книга", book1);
    private static final Comment comment3 = commentOf("3", "Не очень книга", book2);

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CommentRepository repository;

    @Before
    public void init() {
        mongoTemplate.getDb().drop();
        mongoTemplate.insert(comment1);
        mongoTemplate.insert(comment2);
        mongoTemplate.insert(comment3);
    }

    @Test
    public void insertTest() {
        Comment comment = commentOf("Отличная книга!", bookOf("1", "", "", null, null));
        String id = repository.save(comment).getId();
        assertNotEquals(null, id);
    }

    @Test
    public void updateTest() {
        Comment expected = comment1;
        repository.save(expected);
        Comment actual = repository.findById("1").orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTest() {
        Comment expected = comment2;
        Comment actual = repository.findById("2").orElse(null);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getMessage(), actual.getMessage());
        assertEquals(expected.getBook().getId(), actual.getBook().getId());
    }

    @Test
    public void findAllTest() {
        List<Comment> expected = asList(comment1, comment2, comment3);
        List<Comment> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countTest() {
        assertEquals(3, repository.count());
    }

    @Test
    public void deleteByIdTest() {
        List<Comment> expected = asList(comment1, comment3);
        repository.deleteById("2");
        List<Comment> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteAllTest() {
        List<Comment> expected = emptyList();
        repository.deleteAll();
        List<Comment> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findByBookIdTest() {
        List<Comment> expected = asList(comment1, comment2);
        List<Comment> actual = repository.findByBookId("1");
        assertEquals(expected, actual);
    }
}