package ru.spring.dao.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.dao.BookDao;
import ru.spring.dao.CommentDao;
import ru.spring.model.Book;
import ru.spring.model.Comment;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.spring.model.Book.bookOf;
import static ru.spring.model.Comment.commentOf;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("ru.spring.dao")
public class CommentJpaRepositoryTest {
    @Autowired
    private CommentDao dao;
    @Autowired
    private BookDao bookDao;

    @Test
    public void crud() throws Exception {
        Long bookId = bookDao.insert(Book.bookOf("Книга", "Какая-то", null, null));

        Book book = bookOf(1L, "", "", null, null);
        Comment expected = commentOf("Комментарий", book);
        Long id = dao.insert(expected);

        Comment comment = dao.findById(id);
        assertEquals(id, comment.getId());
        assertEquals(expected.getMessage(), comment.getMessage());
        assertEquals(1, dao.count());

        List<Comment> comments = dao.findAll();
        assertEquals(1, comments.size());
        assertEquals(expected.getMessage(), comments.get(0).getMessage());

        dao.deleteById(id);
        comments = dao.findAll();
        assertEquals(0, comments.size());
        assertEquals(0, dao.count());

        dao.insert(commentOf("Комментарий", book));
        dao.insert(commentOf("Комментарий", book));
        comments = dao.findAll();
        assertEquals(2, comments.size());
        assertEquals(2, dao.count());

        dao.deleteAll();
        comments = dao.findAll();
        assertEquals(0, comments.size());
        assertEquals(0, dao.count());
    }
}