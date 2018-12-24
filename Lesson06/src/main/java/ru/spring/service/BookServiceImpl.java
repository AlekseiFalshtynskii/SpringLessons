package ru.spring.service;

import org.springframework.stereotype.Service;
import ru.spring.dao.BookDao;
import ru.spring.model.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao dao;

    public BookServiceImpl(BookDao dao) {
        this.dao = dao;
    }

    @Override
    public Long save(Book book) {
        if (book.getId() == null) {
            return dao.insert(book);
        } else {
            return dao.update(book);
        }
    }

    @Override
    public Book findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return dao.findAll();
    }

    @Override
    public long count() {
        return dao.count();
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}
