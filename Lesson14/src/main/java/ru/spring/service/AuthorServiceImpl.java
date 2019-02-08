package ru.spring.service;

import org.springframework.stereotype.Service;
import ru.spring.model.Author;
import ru.spring.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long save(Author author) {
        return repository.save(author).getId();
    }

    @Override
    public Author findById(Long id) {
        Optional<Author> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Author> findAll() {
        return (List<Author>) repository.findAll();
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
