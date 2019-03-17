package ru.spring.service;

import org.springframework.stereotype.Service;
import ru.spring.model.Genre;
import ru.spring.repository.GenreRepository;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository repository;

    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long save(Genre genre) {
        return repository.save(genre).getId();
    }

    @Override
    public Genre findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Genre> findAll() {
        return repository.findAll();
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
