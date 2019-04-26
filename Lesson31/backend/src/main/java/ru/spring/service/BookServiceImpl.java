package ru.spring.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import ru.spring.model.Book;
import ru.spring.repository.BookRepository;

import java.util.List;

import static java.util.Arrays.asList;
import static ru.spring.model.Author.authorOf;
import static ru.spring.model.Book.bookOf;
import static ru.spring.model.Genre.genreOf;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @HystrixCommand(groupKey = "BookService", commandKey = "save", fallbackMethod = "fallbackSave")
    @Override
    public Book save(Book book) {
        return repository.save(book);
    }

    @HystrixCommand(groupKey = "BookService", commandKey = "findById", fallbackMethod = "fallbackFindById")
    @Override
    public Book findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @HystrixCommand(groupKey = "BookService", commandKey = "findAll", fallbackMethod = "fallbackFindAll")
    @Override
    public List<Book> findAll() {
        return (List<Book>) repository.findAll();
    }

    @HystrixCommand(groupKey = "BookService", commandKey = "count", fallbackMethod = "fallbackCount")
    @Override
    public long count() {
        return repository.count();
    }

    @HystrixCommand(groupKey = "BookService", commandKey = "deleteById")
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @HystrixCommand(groupKey = "BookService", commandKey = "deleteAll")
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    public Book fallbackSave() {
        return book();
    }

    public List<Book> fallbackFindAll() {
        return asList(book());
    }

    public Book fallbackFindById() {
        return book();
    }

    public List<Book> fallbackFindAllById() {
        return asList(book());
    }

    public long fallbackCount() {
        return 1L;
    }

    private Book book() {
        return bookOf(665L,
                "Лучшая книга",
                "Действительно лучшая",
                asList(authorOf(666L, "Ололош", "Ололоев")),
                asList(genreOf(667L, "Лучший жанр"))
        );
    }
}
