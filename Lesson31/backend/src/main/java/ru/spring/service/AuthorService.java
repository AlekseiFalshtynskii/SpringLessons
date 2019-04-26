package ru.spring.service;

import ru.spring.model.Author;

import java.util.List;

public interface AuthorService {
    Author save(Author author);

    Author findById(Long id);

    List<Author> findAll();

    List<Author> findAllById(Iterable<Long> iterable);

    long count();

    void deleteById(Long id);

    void deleteAll();
}
