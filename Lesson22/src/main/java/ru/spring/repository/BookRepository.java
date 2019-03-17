package ru.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.spring.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
