package ru.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.spring.model.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByBookId(String bookId);
}
