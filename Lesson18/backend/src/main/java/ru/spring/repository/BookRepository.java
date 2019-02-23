package ru.spring.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.spring.model.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
