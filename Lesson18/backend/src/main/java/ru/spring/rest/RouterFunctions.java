package ru.spring.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterFunctions {

    @Bean
    RouterFunction<ServerResponse> genreRouterFunction(GenreHandler handler) {
        return route(GET("/genres"), handler::getAll)
                .andRoute(GET("/genres/{id}"), handler::getById)
                .andRoute(POST("/genres"), handler::save)
                .andRoute(DELETE("/genres/{id}"), handler::deleteById)
                .andRoute(DELETE("/genres"), handler::deleteAll);
    }

    @Bean
    RouterFunction<ServerResponse> authorRouterFunction(AuthorHandler handler) {
        return route(GET("/authors"), handler::getAll)
                .andRoute(GET("/authors/{id}"), handler::getById)
                .andRoute(POST("/authors"), handler::save)
                .andRoute(DELETE("/authors/{id}"), handler::deleteById)
                .andRoute(DELETE("/authors"), handler::deleteAll);
    }

    @Bean
    RouterFunction<ServerResponse> bookRouterFunction(BookHandler handler) {
        return route(GET("/books"), handler::getAll)
                .andRoute(GET("/books/{id}"), handler::getById)
                .andRoute(POST("/books"), handler::save)
                .andRoute(DELETE("/books/{id}"), handler::deleteById)
                .andRoute(DELETE("/books"), handler::deleteAll);
    }
}
