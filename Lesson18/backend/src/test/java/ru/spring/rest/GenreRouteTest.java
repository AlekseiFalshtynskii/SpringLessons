package ru.spring.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Mono;
import ru.spring.event.GenreEventListener;
import ru.spring.model.Genre;

import static java.util.Collections.singletonMap;
import static ru.spring.model.Genre.genreOf;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenreRouteTest {

    @Autowired
    private RouterFunction genreRouterFunction;

    @MockBean
    private GenreEventListener listener;

    private WebTestClient client;

    @Before
    public void before() {
        client = WebTestClient.bindToRouterFunction(genreRouterFunction)
                .build();
    }

    @Test
    public void getRouteTest() {
        client.get()
                .uri("/genres")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Genre.class);
    }

    @Test
    public void getWithIdRouteTest() {
        client.get()
                .uri("/genres/{id}", singletonMap("id", "1"))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Genre.class);
    }

    @Test
    public void postRouteTest() {
        client.post().uri("/genres")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(genreOf("name")), Genre.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("name");
    }

    @Test
    public void deleteWithIdRouteTest() {
        client.delete().uri("/genres/{id}", singletonMap("id", "1"))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void deleteRouteTest() {
        client.delete().uri("/genres")
                .exchange()
                .expectStatus().isOk();
    }
}
