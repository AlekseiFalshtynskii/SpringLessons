package ru.spring.dao;

import ru.spring.model.Comment;

import java.util.List;

public interface CommentDao extends Dao<Comment, Long> {
    List<Comment> findByBookId(Long bookId);
}
