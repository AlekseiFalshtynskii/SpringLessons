package ru.spring.service;

import ru.spring.model.Comment;

import java.util.List;

public interface CommentService {
    String save(Comment author);

    Comment findById(String id);

    List<Comment> findByBookId(String bookId);

    List<Comment> findAll();

    long count();

    void deleteById(String id);

    void deleteAll();
}
