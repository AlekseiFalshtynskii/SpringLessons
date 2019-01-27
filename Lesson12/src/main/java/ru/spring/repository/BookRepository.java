package ru.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.spring.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
