package ru.spring.service;

import ru.spring.model.Book;

import java.util.List;

public interface BookService {
    String save(Book book);

    Book findById(String id);

    List<Book> findAll();

    long count();

    void deleteById(String id);

    void deleteAll();
}
