package ru.spring.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.spring.model.Comment;
import ru.spring.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;

    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Comment> save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public Mono<Comment> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Comment> findByBookId(String bookId) {
        return repository.findByBookId(bookId);
    }

    @Override
    public Flux<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Long> count() {
        return repository.count();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }
}
