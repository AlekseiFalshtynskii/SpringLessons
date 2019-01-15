package ru.spring.service;

import ru.spring.model.Comment;

import java.util.List;

public interface CommentService {
    Long save(Comment author);

    Comment findById(Long id);

    List<Comment> findByBookId(Long bookId);

    List<Comment> findAll();

    long count();

    void deleteById(Long id);

    void deleteAll();
}
