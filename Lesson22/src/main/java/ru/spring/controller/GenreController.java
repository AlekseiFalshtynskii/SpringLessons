package ru.spring.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.spring.model.Genre;
import ru.spring.service.GenreService;

import javax.validation.Valid;

import static ru.spring.model.Genre.genreOf;

@Controller
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    public String add(Model model) {
        model.addAttribute("genre", genreOf());
        model.addAttribute("readonly", false);
        model.addAttribute("module", "genres");
        return "genre-edit";
    }

    @GetMapping("/genres/{id}/view")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("genre", genreService.findById(id));
        model.addAttribute("readonly", true);
        model.addAttribute("module", "genres");
        return "genre-edit";
    }

    @GetMapping("/genres/{id}/edit")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("genre", genreService.findById(id));
        model.addAttribute("readonly", false);
        model.addAttribute("module", "genres");
        return "genre-edit";
    }

    @PostMapping(value = "/genres")
    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR', 'EDITOR')")
    public String save(@Valid @ModelAttribute Genre genre, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("module", "genres");
            return "genre-edit";
        }
        genreService.save(genre);
        return "redirect:/genres";
    }

    @DeleteMapping("/genres/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'REMOVER')")
    public String deleteById(@PathVariable Long id) {
        genreService.deleteById(id);
        return "redirect:/genres";
    }

    @DeleteMapping("/genres")
    @PreAuthorize("hasAnyRole('ADMIN', 'REMOVER')")
    public String deleteAll() {
        genreService.deleteAll();
        return "redirect:/genres";
    }
}
