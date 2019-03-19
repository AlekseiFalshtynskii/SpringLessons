package ru.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
