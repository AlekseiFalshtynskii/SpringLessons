package ru.spring.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.spring.model.Author;
import ru.spring.model.Book;
import ru.spring.model.Genre;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ru.spring.model.Author.authorOf;
import static ru.spring.model.Book.bookOf;
import static ru.spring.model.Genre.genreOf;

@RunWith(SpringRunner.class)
@DataMongoTest
public class BookRepositoryTest {
    private static final Author author1 = authorOf("1", "Уоллс", "Крейг");
    private static final Author author2 = authorOf("2", "Жемеров", "Дмитрий");
    private static final Author author3 = authorOf("3", "Исакова", "Светлана");
    private static final Genre genre1 = genreOf("1", "Программирование");
    private static final Book book1 = bookOf("1", "Spring в действии", "", asList(author1), asList(genre1));
    private static final Book book2 = bookOf("2", "Kotlin в действии", "", asList(author2, author3), asList(genre1));

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BookRepository repository;


    @Before
    public void init() {
        mongoTemplate.getDb().drop();
        mongoTemplate.insert(author1);
        mongoTemplate.insert(author2);
        mongoTemplate.insert(author3);
        mongoTemplate.insert(genre1);
        mongoTemplate.insert(book1);
        mongoTemplate.insert(book2);
    }

    @Test
    public void saveTest() {
        Mono<Book> bookMono = repository.save(bookOf("Node.js в действии", "Описание", asList(author1), asList(genre1)));

        StepVerifier
                .create(bookMono)
                .assertNext(book -> assertNotNull(book.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    public void updateTest() {
        Book expected = bookOf("1", "Spring в действии 2", "Описание", asList(author1), asList(genre1));
        Mono<Book> bookMono = repository.save(expected);

        StepVerifier
                .create(bookMono)
                .assertNext(book -> assertEquals(expected, book))
                .expectComplete()
                .verify();
    }

    @Test
    public void findByIdTest() {
        Mono<Book> bookMono = repository.findById("1");

        StepVerifier
                .create(bookMono)
                .assertNext(book -> assertEquals(book1, book))
                .expectComplete()
                .verify();
    }

    @Test
    public void findAllTest() {
        Flux<Book> bookFlux = repository.findAll();

        StepVerifier
                .create(bookFlux)
                .expectNext(book1, book2)
                .expectComplete()
                .verify();
    }

    @Test
    public void countTest() {
        Mono<Long> longMono = repository.count();

        StepVerifier
                .create(longMono)
                .expectNext(2L)
                .expectComplete()
                .verify();
    }

    @Test
    public void deleteByIdTest() {
        repository.deleteById("1").subscribe();
        Flux<Book> bookFlux = repository.findAll();

        StepVerifier
                .create(bookFlux)
                .expectNext(book2)
                .expectComplete()
                .verify();
    }

    @Test
    public void deleteAllTest() {
        repository.deleteAll().subscribe();
        Flux<Book> bookFlux = repository.findAll();

        StepVerifier
                .create(bookFlux)
                .expectNextCount(0L)
                .expectComplete()
                .verify();
    }
}
