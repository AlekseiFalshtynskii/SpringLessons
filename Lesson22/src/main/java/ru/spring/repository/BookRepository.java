package ru.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
