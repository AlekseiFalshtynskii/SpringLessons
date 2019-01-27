package ru.spring.service;

import ru.spring.model.Genre;

import java.util.List;

public interface GenreService {
    String save(Genre genre);

    Genre findById(String id);

    List<Genre> findAll();

    long count();

    void deleteById(String id);

    void deleteAll();
}
