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
import ru.spring.event.BookEventListener;
import ru.spring.model.Book;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonMap;
import static ru.spring.model.Book.bookOf;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRouteTest {

    @Autowired
    private RouterFunction bookRouterFunction;

    @MockBean
    private BookEventListener listener;

    private WebTestClient client;

    @Before
    public void before() {
        client = WebTestClient.bindToRouterFunction(bookRouterFunction)
                .build();
    }

    @Test
    public void getRouteTest() {
        client.get()
                .uri("/books")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Book.class);
    }

    @Test
    public void getWithIdRouteTest() {
        client.get()
                .uri("/books/{id}", singletonMap("id", "1"))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Book.class);
    }

    @Test
    public void postRouteTest() {
        client.post().uri("/books")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(bookOf("Название", "Описание", emptyList(), emptyList())), Book.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("Название")
                .jsonPath("$.description").isEqualTo("Описание");
    }

    @Test
    public void deleteWithIdRouteTest() {
        client.delete().uri("/books/{id}", singletonMap("id", "1"))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void deleteRouteTest() {
        client.delete().uri("/books")
                .exchange()
                .expectStatus().isOk();
    }
}
