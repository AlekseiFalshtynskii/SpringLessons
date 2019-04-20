package ru.spring.controller;

import org.springframework.web.bind.annotation.*;
import ru.spring.model.Author;
import ru.spring.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public List<Author> getAll() {
        return authorService.findAll();
    }

    @GetMapping("/authors/{id}")
    public Author getById(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @PostMapping("/authors")
    public Author save(@RequestBody Author author) {
        return authorService.save(author);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
    }

    @DeleteMapping("/authors")
    public void deleteAll() {
        authorService.deleteAll();
    }
}
