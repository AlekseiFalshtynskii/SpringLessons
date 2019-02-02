package ru.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.spring.model.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
