package ru.spring.service;

import ru.spring.model.Genre;

import java.util.List;

public interface GenreService {
    Genre save(Genre genre);

    Genre findById(Long id);

    List<Genre> findAll();

    List<Genre> findAllById(Iterable<Long> iterable);

    long count();

    void deleteById(Long id);

    void deleteAll();
}
