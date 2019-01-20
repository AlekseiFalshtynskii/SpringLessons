package ru.spring.service;

import ru.spring.model.Author;

import java.util.List;

public interface AuthorService {
    Long save(Author author);

    Author findById(Long id);

    List findAll();

    long count();

    void deleteById(Long id);

    void deleteAll();
}
