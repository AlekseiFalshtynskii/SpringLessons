package ru.spring.service;

import ru.spring.model.Author;

import java.util.List;

public interface AuthorService {
    String save(Author author);

    Author findById(String id);

    List findAll();

    long count();

    void deleteById(String id);

    void deleteAll();
}
