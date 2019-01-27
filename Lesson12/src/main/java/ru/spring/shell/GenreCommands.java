package ru.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.spring.model.Genre;
import ru.spring.service.GenreService;

@ShellComponent
public class GenreCommands {
    private final GenreService genreService;

    public GenreCommands(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod("Save genre")
    public String saveGenre(@ShellOption Genre genre) {
        genreService.save(genre);
        return "\nSaved " + genre;
    }

    @ShellMethod("Find genre by id")
    public String findByIdGenre(@ShellOption String id) {
        Genre genre = genreService.findById(id);
        return genre == null ? "Genre not found by id = " + id : "Finded " + genre.toString();
    }

    @ShellMethod("Find all genres")
    public String findAllGenre() {
        return genreService.findAll().toString();
    }

    @ShellMethod("Get count of genres")
    public long countGenre() {
        return genreService.count();
    }

    @ShellMethod("Delete genre by id")
    public String deleteByIdGenre(String id) {
        genreService.deleteById(id);
        return "Deleted genre by id = " + id;
    }

    @ShellMethod("Delete all genres")
    public String deleteAllGenre() {
        genreService.deleteAll();
        return "Deleted all genres";
    }
}
