package ru.spring.service;

import ru.spring.model.Book;

import java.util.List;

public interface BookService {
    Long save(Book book);

    Book findById(Long id);

    List<Book> findAll();

    long count();

    void deleteById(Long id);

    void deleteAll();
}
