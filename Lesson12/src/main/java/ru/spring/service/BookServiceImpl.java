package ru.spring.service;

import org.springframework.stereotype.Service;
import ru.spring.model.Book;
import ru.spring.repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public String save(Book book) {
        return repository.save(book).getId();
    }

    @Override
    public Book findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
