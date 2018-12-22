package ru.spring.service;

import ru.spring.model.Author;

import java.util.List;

public interface AuthorService {
    Integer save(Author author);

    Author findById(Integer id);

    List<Author> findAll();

    long count();

    void deleteById(Integer id);

    void deleteAll();
}
