package ru.spring.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import ru.spring.model.Genre;
import ru.spring.repository.GenreRepository;

import java.util.List;

import static java.util.Arrays.asList;
import static ru.spring.model.Genre.genreOf;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository repository;

    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @HystrixCommand(groupKey = "GenreService", commandKey = "save")
    @Override
    public Genre save(Genre genre) {
        return repository.save(genre);
    }

    @HystrixCommand(groupKey = "GenreService", commandKey = "findById", fallbackMethod = "fallbackFindById")
    @Override
    public Genre findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @HystrixCommand(groupKey = "GenreService", commandKey = "findAll", fallbackMethod = "fallbackFindAll")
    @Override
    public List<Genre> findAll() {
        return repository.findAll();
    }

    @HystrixCommand(groupKey = "GenreService", commandKey = "findAllById", fallbackMethod = "fallbackFindAllById")
    @Override
    public List<Genre> findAllById(Iterable<Long> iterable) {
        return repository.findAllById(iterable);
    }

    @HystrixCommand(groupKey = "GenreService", commandKey = "count", fallbackMethod = "fallbackCount")
    @Override
    public long count() {
        return repository.count();
    }

    @HystrixCommand(groupKey = "GenreService", commandKey = "deleteById")
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @HystrixCommand(groupKey = "GenreService", commandKey = "deleteAll")
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Genre> fallbackFindAll() {
        return asList(genre());
    }

    public Genre fallbackFindById() {
        return genre();
    }

    public List<Genre> fallbackFindAllById() {
        return asList(genre());
    }

    public long fallbackCount() {
        return 1L;
    }

    private Genre genre() {
        return genreOf(667L, "Лучший жанр");
    }
}
