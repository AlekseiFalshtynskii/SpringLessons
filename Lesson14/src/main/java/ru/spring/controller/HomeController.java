package ru.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.spring.service.AuthorService;
import ru.spring.service.BookService;
import ru.spring.service.GenreService;

@Controller
public class HomeController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public HomeController(BookService bookService,
                          AuthorService authorService,
                          GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping(value = {"/", "/books"})
    public String books(Model model) {
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("module", "books");
        return "books";
    }

    @GetMapping("/authors")
    public String authors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("module", "authors");
        return "authors";
    }

    @GetMapping("/genres")
    public String genres(Model model) {
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("module", "genres");
        return "genres";
    }
}
