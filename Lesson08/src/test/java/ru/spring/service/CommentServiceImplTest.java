package ru.spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.dao.CommentDao;
import ru.spring.model.Comment;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static ru.spring.model.Comment.commentOf;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class CommentServiceImplTest {
    @MockBean
    private CommentDao daoMock;
    @Autowired
    private CommentService commentService;

    @Test
    public void saveTest() throws Exception {
        Comment expected = commentOf("", 0L);
        when(daoMock.insert(expected)).thenReturn(1L);
        when(daoMock.update(expected)).thenReturn(1L);
        Long id = commentService.save(expected);
        assertEquals(1, id.intValue());
        verify(daoMock, times(1)).insert(expected);

        expected = commentOf(2L, "", 0L);
        when(daoMock.insert(expected)).thenReturn(2L);
        when(daoMock.update(expected)).thenReturn(2L);
        id = commentService.save(expected);
        assertEquals(2, id.intValue());
        verify(daoMock, times(1)).update(expected);
    }

    @Test
    public void findByIdTest() throws Exception {
        Comment expected = commentOf(1L, "", 0L);
        when(daoMock.findById(1L)).thenReturn(expected);
        Comment comment = commentService.findById(1L);
        assertEquals(expected, comment);
        verify(daoMock, times(1)).findById(1L);
    }

    @Test
    public void findAllTest() throws Exception {
        List<Comment> expected = emptyList();
        when(daoMock.findAll()).thenReturn(expected);
        List<Comment> comments = commentService.findAll();
        assertEquals(expected, comments);
        verify(daoMock, times(1)).findAll();
    }

    @Test
    public void countTest() throws Exception {
        long expected = 1;
        when(daoMock.count()).thenReturn(expected);
        long count = commentService.count();
        assertEquals(expected, count);
        verify(daoMock, times(1)).count();
    }

    @Test
    public void deleteByIdTest() throws Exception {
        commentService.deleteById(1L);
        verify(daoMock, times(1)).deleteById(1L);
    }

    @Test
    public void deleteAllTest() throws Exception {
        commentService.deleteAll();
        verify(daoMock, times(1)).deleteAll();
    }
}