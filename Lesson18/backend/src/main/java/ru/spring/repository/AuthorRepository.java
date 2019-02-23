package ru.spring.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.spring.model.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
}
