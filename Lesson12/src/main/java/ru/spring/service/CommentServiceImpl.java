package ru.spring.service;

import org.springframework.stereotype.Service;
import ru.spring.model.Comment;
import ru.spring.repository.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;

    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public String save(Comment comment) {
        return repository.save(comment).getId();
    }

    @Override
    public Comment findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> findByBookId(String bookId) {
        return repository.findByBookId(bookId);
    }

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
