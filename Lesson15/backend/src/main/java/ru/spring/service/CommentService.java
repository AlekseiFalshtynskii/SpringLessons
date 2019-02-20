package ru.spring.service;

import ru.spring.model.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);

    Comment findById(Long id);

    List<Comment> findByBookId(Long id);

    List<Comment> findAll();

    long count();

    void deleteById(Long id);

    void deleteAll();
}
