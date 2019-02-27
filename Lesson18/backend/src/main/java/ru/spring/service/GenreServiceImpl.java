package ru.spring.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.spring.model.Genre;
import ru.spring.repository.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository repository;

    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Genre> save(Genre genre) {
        return repository.save(genre);
    }

    @Override
    public Mono<Genre> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Genre> findAll() {
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
