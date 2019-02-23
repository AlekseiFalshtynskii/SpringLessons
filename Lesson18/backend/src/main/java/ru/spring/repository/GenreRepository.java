package ru.spring.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.spring.model.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
}
