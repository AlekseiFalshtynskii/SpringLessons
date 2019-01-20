package ru.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
