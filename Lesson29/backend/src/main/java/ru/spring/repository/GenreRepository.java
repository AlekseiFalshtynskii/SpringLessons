package ru.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
