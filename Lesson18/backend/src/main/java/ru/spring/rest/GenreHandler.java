package ru.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.spring.model.Genre;
import ru.spring.service.GenreService;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class GenreHandler {

    private final GenreService genreService;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ok().body(genreService.findAll(), Genre.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        return ok().body(genreService.findById(request.pathVariable("id")), Genre.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Genre> genre = request.bodyToMono(Genre.class);
        genre = genre.flatMap(genreService::save);
        return ok().body(genre, Genre.class);
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        return ok().body(genreService.deleteById(request.pathVariable("id")), Void.class);
    }

    public Mono<ServerResponse> deleteAll(ServerRequest request) {
        return ok().body(genreService.deleteAll(), Void.class);
    }
}
