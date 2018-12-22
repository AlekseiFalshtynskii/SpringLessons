package ru.spring.service;

import org.springframework.stereotype.Service;
import ru.spring.dao.jdbc.BookJdbcDao;
import ru.spring.model.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookJdbcDao dao;

    public BookServiceImpl(BookJdbcDao dao) {
        this.dao = dao;
    }

    @Override
    public Integer save(Book book) {
        if (book.getId() == null) {
            return dao.insert(book);
        } else {
            return dao.update(book);
        }
    }

    @Override
    public Book findById(Integer id) {
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
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}
