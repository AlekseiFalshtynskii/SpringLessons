package ru.spring.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.spring.model.Author;

public interface AuthorService {

    Mono<Author> save(Author author);

    Mono<Author> findById(String id);

    Flux<Author> findAll();

    Mono<Long> count();

    Mono<Void> deleteById(String id);

    Mono<Void> deleteAll();
}
