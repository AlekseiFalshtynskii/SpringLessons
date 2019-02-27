package ru.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.spring.model.Author;
import ru.spring.service.AuthorService;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class AuthorHandler {

    private final AuthorService authorService;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ok().body(authorService.findAll(), Author.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        return ok().body(authorService.findById(request.pathVariable("id")), Author.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Author> author = request.bodyToMono(Author.class);
        author = author.flatMap(authorService::save);
        return ok().body(author, Author.class);
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        return ok().body(authorService.deleteById(request.pathVariable("id")), Void.class);
    }

    public Mono<ServerResponse> deleteAll(ServerRequest request) {
        return ok().body(authorService.deleteAll(), Void.class);
    }
}
