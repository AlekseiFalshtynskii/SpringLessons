package ru.spring.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.spring.model.Author;
import ru.spring.service.AuthorService;

import javax.validation.Valid;

import static ru.spring.model.Author.authorOf;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    public String add(Model model) {
        model.addAttribute("author", authorOf());
        model.addAttribute("readonly", false);
        model.addAttribute("module", "authors");
        return "author-edit";
    }

    @GetMapping("/authors/{id}/view")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        model.addAttribute("readonly", true);
        model.addAttribute("module", "authors");
        return "author-edit";
    }

    @GetMapping("/authors/{id}/edit")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        model.addAttribute("readonly", false);
        model.addAttribute("module", "authors");
        return "author-edit";
    }

    @PostMapping("/authors")
    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR', 'EDITOR')")
    public String save(@Valid @ModelAttribute Author author, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("module", "authors");
            return "author-edit";
        }
        authorService.save(author);
        return "redirect:/authors";
    }

    @DeleteMapping("/authors/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'REMOVER')")
    public String deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }

    @DeleteMapping("/authors")
    @PreAuthorize("hasAnyRole('ADMIN', 'REMOVER')")
    public String deleteAll() {
        authorService.deleteAll();
        return "redirect:/authors";
    }
}
