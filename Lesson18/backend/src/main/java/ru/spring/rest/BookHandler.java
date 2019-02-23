package ru.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.spring.model.Book;
import ru.spring.service.BookService;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class BookHandler {

    private final BookService bookService;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ok().body(bookService.findAll(), Book.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        return ok().body(bookService.findById(request.pathVariable("id")), Book.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Book> book = request.bodyToMono(Book.class);
        book = book.flatMap(bookService::save);
        return ok().body(book, Book.class);
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        return ok().body(bookService.deleteById(request.pathVariable("id")), Void.class);
    }

    public Mono<ServerResponse> deleteAll(ServerRequest request) {
        return ok().body(bookService.deleteAll(), Void.class);
    }
}
