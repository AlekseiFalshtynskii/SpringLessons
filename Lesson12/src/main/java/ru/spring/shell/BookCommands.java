package ru.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.spring.model.Book;
import ru.spring.service.BookService;

@ShellComponent
public class BookCommands {
    private final BookService bookService;

    public BookCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod("Save book")
    public String saveBook(@ShellOption Book book) {
        bookService.save(book);
        return "\nSaved " + book;
    }

    @ShellMethod("Find book by id")
    public String findByIdBook(@ShellOption String id) {
        Book book = bookService.findById(id);
        return book == null ? "Book not found by id = " + id : "Finded " + book.toString();
    }

    @ShellMethod("Find all books")
    public String findAllBook() {
        return bookService.findAll().toString();
    }

    @ShellMethod("Get count of books")
    public long countBook() {
        return bookService.count();
    }

    @ShellMethod("Delete book by id")
    public String deleteByIdBook(String id) {
        bookService.deleteById(id);
        return "Deleted book by id = " + id;
    }

    @ShellMethod("Delete all books")
    public String deleteAllBook() {
        bookService.deleteAll();
        return "Deleted all books";
    }
}
