package ru.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.model.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
