package ru.spring.service;

import org.springframework.stereotype.Service;
import ru.spring.model.Book;
import ru.spring.repository.AuthorRepository;
import ru.spring.repository.BookRepository;
import ru.spring.repository.CommentRepository;
import ru.spring.repository.GenreRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    public BookServiceImpl(BookRepository repository,
                           AuthorRepository authorRepository,
                           GenreRepository genreRepository,
                           CommentRepository commentRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public String save(Book book) {
        if (book.getAuthors() != null) {
            book.setAuthors(authorRepository.saveAll(book.getAuthors()));
        }
        if (book.getGenres() != null) {
            book.setGenres(genreRepository.saveAll(book.getGenres()));
        }
        return repository.save(book).getId();
    }

    @Override
    public Book findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteAll(commentRepository.findByBookId(id));
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        commentRepository.deleteAll();
        repository.deleteAll();
    }
}
