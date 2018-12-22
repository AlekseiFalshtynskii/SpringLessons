package ru.spring.service;

import org.springframework.stereotype.Service;
import ru.spring.dao.jdbc.GenreJdbcDao;
import ru.spring.model.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreJdbcDao dao;

    public GenreServiceImpl(GenreJdbcDao dao) {
        this.dao = dao;
    }

    @Override
    public Integer save(Genre genre) {
        if (genre.getId() == null) {
            return dao.insert(genre);
        } else {
            return dao.update(genre);
        }
    }

    @Override
    public Genre findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<Genre> findAll() {
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
