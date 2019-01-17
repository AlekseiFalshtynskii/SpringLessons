package ru.spring.service;

import org.springframework.stereotype.Service;
import ru.spring.dao.AuthorDao;
import ru.spring.model.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao dao;

    public AuthorServiceImpl(AuthorDao dao) {
        this.dao = dao;
    }

    @Override
    public Long save(Author author) {
        if (author.getId() == null) {
            return dao.insert(author);
        } else {
            return dao.update(author);
        }
    }

    @Override
    public Author findById(Long id) {
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
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}
