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
    public Genre save(Genre genre) {
        return repository.save(genre);
    }

    @Override
    public Genre findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Genre> findAll() {
        return (List<Genre>) repository.findAll();
    }

    @Override
    public List<Genre> findAllById(Iterable<Long> iterable) {
        return repository.findAllById(iterable);
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
