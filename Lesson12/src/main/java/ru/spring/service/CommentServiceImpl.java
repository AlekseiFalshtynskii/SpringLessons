package ru.spring.service;

import org.springframework.stereotype.Service;
import ru.spring.model.Comment;
import ru.spring.repository.BookRepository;
import ru.spring.repository.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final BookRepository bookRepository;

    public CommentServiceImpl(CommentRepository repository,
                              BookRepository bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @Override
    public String save(Comment comment) {
        comment.setBook(bookRepository.findById(comment.getBook().getId()).orElse(null));
        return repository.save(comment).getId();
    }

    @Override
    public Comment findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> findByBookId(String bookId) {
        return repository.findByBookId(bookId);
    }

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
