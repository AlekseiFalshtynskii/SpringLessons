package ru.spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.model.Comment;
import ru.spring.repository.CommentRepository;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static ru.spring.model.Book.bookOf;
import static ru.spring.model.Comment.commentOf;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class CommentServiceImplTest {
    @MockBean
    private CommentRepository repositoryMock;
    @Autowired
    private CommentService commentService;

    @Test
    public void saveTest() throws Exception {
        Comment comment1 = commentOf("", bookOf(1L, "", "", null, null));
        Comment comment2 = commentOf(1L, "", bookOf(1L, "", "", null, null));
        when(repositoryMock.save(comment1)).thenReturn(comment2);
        Long id = commentService.save(comment1);
        assertEquals(1, id.intValue());
        verify(repositoryMock, times(1)).save(comment1);
    }

    @Test
    public void findByIdTest() throws Exception {
        Comment expected = commentOf(1L, "", bookOf(1L, "", "", null, null));
        when(repositoryMock.findById(1L)).thenReturn(of(expected));
        Comment comment = commentService.findById(1L);
        assertEquals(expected, comment);
        verify(repositoryMock, times(1)).findById(1L);
    }

    @Test
    public void findAllTest() throws Exception {
        List<Comment> expected = emptyList();
        when(repositoryMock.findAll()).thenReturn(expected);
        List<Comment> comments = commentService.findAll();
        assertEquals(expected, comments);
        verify(repositoryMock, times(1)).findAll();
    }

    @Test
    public void countTest() throws Exception {
        long expected = 1;
        when(repositoryMock.count()).thenReturn(expected);
        long count = commentService.count();
        assertEquals(expected, count);
        verify(repositoryMock, times(1)).count();
    }

    @Test
    public void deleteByIdTest() throws Exception {
        commentService.deleteById(1L);
        verify(repositoryMock, times(1)).deleteById(1L);
    }

    @Test
    public void deleteAllTest() throws Exception {
        commentService.deleteAll();
        verify(repositoryMock, times(1)).deleteAll();
    }

    @Test
    public void findByBookIdTest() throws Exception {
        List<Comment> expected = emptyList();
        when(repositoryMock.findByBookId(1L)).thenReturn(expected);
        List<Comment> comments = commentService.findByBookId(1L);
        assertEquals(expected, comments);
        verify(repositoryMock, times(1)).findByBookId(1L);
    }
}