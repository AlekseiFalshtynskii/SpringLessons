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
import ru.spring.event.AuthorEventListener;
import ru.spring.model.Author;

import static java.util.Collections.singletonMap;
import static ru.spring.model.Author.authorOf;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorRouteTest {

    @Autowired
    private RouterFunction authorRouterFunction;

    @MockBean
    private AuthorEventListener listener;

    private WebTestClient client;

    @Before
    public void before() {
        client = WebTestClient.bindToRouterFunction(authorRouterFunction)
                .build();
    }

    @Test
    public void getRouteTest() {
        client.get()
                .uri("/authors")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Author.class);
    }

    @Test
    public void getWithIdRouteTest() {
        client.get()
                .uri("/authors/{id}", singletonMap("id", "1"))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Author.class);
    }

    @Test
    public void postRouteTest() {
        client.post().uri("/authors")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(authorOf("Фамилия", "Имя")), Author.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.lastName").isEqualTo("Фамилия")
                .jsonPath("$.firstName").isEqualTo("Имя");
    }

    @Test
    public void deleteWithIdRouteTest() {
        client.delete().uri("/authors/{id}", singletonMap("id", "1"))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void deleteRouteTest() {
        client.delete().uri("/authors")
                .exchange()
                .expectStatus().isOk();
    }
}
