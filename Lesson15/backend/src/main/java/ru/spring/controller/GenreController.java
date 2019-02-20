package ru.spring.controller;

import org.springframework.web.bind.annotation.*;
import ru.spring.model.Genre;
import ru.spring.service.GenreService;

import java.util.List;

@RestController
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public List<Genre> getAll() {
        return genreService.findAll();
    }

    @GetMapping("/genres/{id}")
    public Genre getById(@PathVariable Long id) {
        return genreService.findById(id);
    }

    @PostMapping("/genres")
    public Genre save(@RequestBody Genre genre) {
        return genreService.save(genre);
    }

    @DeleteMapping("/genres/{id}")
    public void deleteById(@PathVariable Long id) {
        genreService.deleteById(id);
    }

    @DeleteMapping("/genres")
    public void deleteAll() {
        genreService.deleteAll();
    }
}
