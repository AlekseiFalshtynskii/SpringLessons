package ru.spring.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
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
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("readonly", false);
        model.addAttribute("module", "books");
        return "book-edit";
    }

    @PostMapping("/books")
    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR', 'EDITOR')")
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
    @PreAuthorize("hasAnyRole('ADMIN', 'REMOVER')")
    public String deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/";
    }

    @DeleteMapping("/books")
    @PreAuthorize("hasAnyRole('ADMIN', 'REMOVER')")
    public String deleteAll() {
        bookService.deleteAll();
        return "redirect:/";
    }
}
