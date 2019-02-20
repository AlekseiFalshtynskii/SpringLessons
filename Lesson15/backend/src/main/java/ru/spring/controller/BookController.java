package ru.spring.controller;

import org.springframework.web.bind.annotation.*;
import ru.spring.model.Book;
import ru.spring.service.BookService;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping("/books")
    public Book save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @DeleteMapping("/books")
    public void deleteAll() {
        bookService.deleteAll();
    }
}
