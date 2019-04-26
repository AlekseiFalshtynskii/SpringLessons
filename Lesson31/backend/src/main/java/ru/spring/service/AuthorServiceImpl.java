package ru.spring.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import ru.spring.model.Author;
import ru.spring.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static ru.spring.model.Author.authorOf;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @HystrixCommand(groupKey = "AuthorService", commandKey = "save")
    @Override
    public Author save(Author author) {
        return repository.save(author);
    }

    @HystrixCommand(groupKey = "AuthorService", commandKey = "findById", fallbackMethod = "fallbackFindById")
    @Override
    public Author findById(Long id) {
        Optional<Author> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @HystrixCommand(groupKey = "AuthorService", commandKey = "findAll", fallbackMethod = "fallbackFindAll")
    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }

    @HystrixCommand(groupKey = "AuthorService", commandKey = "findAllById", fallbackMethod = "fallbackFindAllById")
    @Override
    public List<Author> findAllById(Iterable<Long> iterable) {
        return repository.findAllById(iterable);
    }

    @HystrixCommand(groupKey = "AuthorService", commandKey = "count", fallbackMethod = "fallbackCount")
    @Override
    public long count() {
        return repository.count();
    }

    @HystrixCommand(groupKey = "AuthorService", commandKey = "deleteById")
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @HystrixCommand(groupKey = "AuthorService", commandKey = "deleteAll")
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Author> fallbackFindAll() {
        return asList(author());
    }

    public Author fallbackFindById() {
        return author();
    }

    public List<Author> fallbackFindAllById() {
        return asList(author());
    }

    public long fallbackCount() {
        return 1L;
    }

    private Author author() {
        return authorOf(666L, "Ололош", "Ололоев");
    }
}
