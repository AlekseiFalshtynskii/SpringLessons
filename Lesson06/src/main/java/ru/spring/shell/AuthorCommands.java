package ru.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.spring.model.Author;
import ru.spring.service.AuthorService;

@ShellComponent
public class AuthorCommands {
    private final AuthorService authorService;

    public AuthorCommands(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod("Save author")
    public String saveAuthor(@ShellOption Author author) {
        authorService.save(author);
        return "\nSaved " + author;
    }

    @ShellMethod("Find author by id")
    public String findByIdAuthor(@ShellOption Long id) {
        Author author = authorService.findById(id);
        return author == null ? "Author not found by id = " + id : "Finded " + author.toString();
    }

    @ShellMethod("Find all authors")
    public String findAllAuthor() {
        return authorService.findAll().toString();
    }

    @ShellMethod("Get count of authors")
    public long countAuthor() {
        return authorService.count();
    }

    @ShellMethod("Delete author by id")
    public String deleteByIdAuthor(Long id) {
        authorService.deleteById(id);
        return "Deleted author by id = " + id;
    }

    @ShellMethod("Delete all authors")
    public String deleteAllAuthor() {
        authorService.deleteAll();
        return "Deleted all authors";
    }
}
