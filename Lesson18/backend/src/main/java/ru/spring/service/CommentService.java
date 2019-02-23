package ru.spring.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.spring.model.Comment;

public interface CommentService {
    Mono<Comment> save(Comment author);

    Mono<Comment> findById(String id);

    Flux<Comment> findByBookId(String bookId);

    Flux<Comment> findAll();

    Mono<Long> count();

    Mono<Void> deleteById(String id);

    Mono<Void> deleteAll();
}
