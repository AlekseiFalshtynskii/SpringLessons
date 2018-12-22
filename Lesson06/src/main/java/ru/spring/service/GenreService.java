package ru.spring.service;

import ru.spring.model.Genre;

import java.util.List;

public interface GenreService {
    Integer save(Genre genre);

    Genre findById(Integer id);

    List<Genre> findAll();

    long count();

    void deleteById(Integer id);

    void deleteAll();
}
