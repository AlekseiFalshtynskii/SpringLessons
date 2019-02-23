package ru.spring.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.spring.model.Author;
import ru.spring.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Author> save(Author author) {
        return repository.save(author);
    }

    @Override
    public Mono<Author> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Author> findAll() {
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
