package ru.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.spring.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
