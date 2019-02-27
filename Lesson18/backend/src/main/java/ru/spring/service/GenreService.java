package ru.spring.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.spring.model.Genre;

public interface GenreService {
    Mono<Genre> save(Genre genre);

    Mono<Genre> findById(String id);

    Flux<Genre> findAll();

    Mono<Long> count();

    Mono<Void> deleteById(String id);

    Mono<Void> deleteAll();
}
