package ru.spring.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.spring.model.Book;

public interface BookService {
    Mono<Book> save(Book book);

    Mono<Book> findById(String id);

    Flux<Book> findAll();

    Mono<Long> count();

    Mono<Void> deleteById(String id);

    Mono<Void> deleteAll();
}
