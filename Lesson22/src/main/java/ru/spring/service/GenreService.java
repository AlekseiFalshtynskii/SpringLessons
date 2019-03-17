package ru.spring.service;

import ru.spring.model.Genre;

import java.util.List;

public interface GenreService {
    Long save(Genre genre);

    Genre findById(Long id);

    List<Genre> findAll();

    long count();

    void deleteById(Long id);

    void deleteAll();
}
