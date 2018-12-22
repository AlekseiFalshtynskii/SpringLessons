package ru.spring.service;

import ru.spring.model.Book;

import java.util.List;

public interface BookService {
    Integer save(Book book);

    Book findById(Integer id);

    List<Book> findAll();

    long count();

    void deleteById(Integer id);

    void deleteAll();
}
