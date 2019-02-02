package ru.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.spring.model.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
