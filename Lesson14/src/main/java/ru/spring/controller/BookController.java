package ru.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.spring.model.Book;
import ru.spring.service.AuthorService;
import ru.spring.service.BookService;
import ru.spring.service.GenreService;

import javax.validation.Valid;

import static ru.spring.model.Book.bookOf;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(BookService bookService,
                          AuthorService authorService,
                          GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/books/add")
    public String add(Model model) {
        model.addAttribute("book", bookOf());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("readonly", false);
        model.addAttribute("module", "books");
        return "book-edit";
    }

    @GetMapping("/books/{id}/view")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("readonly", true);
        model.addAttribute("module", "books");
        return "book-edit";
    }

    @GetMapping("/books/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("readonly", false);
        model.addAttribute("module", "books");
        return "book-edit";
    }

    @PostMapping("/books")
    public String save(@Valid @ModelAttribute Book book, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("genres", genreService.findAll());
            model.addAttribute("module", "books");
            return "book-edit";
        }
        bookService.save(book);
        return "redirect:/";
    }

    @DeleteMapping("/books/{id}")
    public String deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/";
    }

    @DeleteMapping("/books")
    public String deleteAll() {
        bookService.deleteAll();
        return "redirect:/";
    }
}
