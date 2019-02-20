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
    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return (List<Book>) repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
