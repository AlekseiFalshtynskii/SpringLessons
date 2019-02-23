package ru.spring.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.spring.model.Book;
import ru.spring.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Book> save(Book book) {
        return repository.save(book);
    }

    @Override
    public Mono<Book> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Book> findAll() {
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
