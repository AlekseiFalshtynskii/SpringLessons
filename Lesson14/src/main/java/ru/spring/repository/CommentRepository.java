package ru.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.model.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByBookId(Long bookId);
}
