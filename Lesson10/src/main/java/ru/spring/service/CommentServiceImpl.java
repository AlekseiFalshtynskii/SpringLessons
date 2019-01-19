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
    public Long save(Comment comment) {
        return repository.save(comment).getId();
    }

    @Override
    public Comment findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> findByBookId(Long bookId) {
        return repository.findByBookId(bookId);
    }

    @Override
    public List<Comment> findAll() {
        return (List<Comment>) repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
