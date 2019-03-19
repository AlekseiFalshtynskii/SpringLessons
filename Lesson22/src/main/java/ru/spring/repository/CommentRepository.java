package ru.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBookId(Long bookId);
}
