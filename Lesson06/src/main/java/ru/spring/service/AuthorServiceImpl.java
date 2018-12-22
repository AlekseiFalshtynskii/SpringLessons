package ru.spring.service;

import org.springframework.stereotype.Service;
import ru.spring.dao.jdbc.AuthorJdbcDao;
import ru.spring.model.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorJdbcDao dao;

    public AuthorServiceImpl(AuthorJdbcDao dao) {
        this.dao = dao;
    }

    @Override
    public Integer save(Author author) {
        if (author.getId() == null) {
            return dao.insert(author);
        } else {
            return dao.update(author);
        }
    }

    @Override
    public Author findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<Author> findAll() {
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
