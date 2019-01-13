package ru.spring.service;

import org.springframework.stereotype.Service;
import ru.spring.dao.CommentDao;
import ru.spring.model.Comment;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentDao dao;

    public CommentServiceImpl(CommentDao dao) {
        this.dao = dao;
    }

    @Override
    public Long save(Comment comment) {
        if (comment.getId() == null) {
            return dao.insert(comment);
        } else {
            return dao.update(comment);
        }
    }

    @Override
    public Comment findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Comment> findAll() {
        return dao.findAll();
    }

    @Override
    public long count() {
        return dao.count();
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}
