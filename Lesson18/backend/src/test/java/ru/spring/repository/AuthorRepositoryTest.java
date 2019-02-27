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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ru.spring.model.Author.authorOf;

@RunWith(SpringRunner.class)
@DataMongoTest
public class AuthorRepositoryTest {
    private static final Author author1 = authorOf("1", "Уоллс", "Крейг");
    private static final Author author2 = authorOf("2", "Жемеров", "Дмитрий");
    private static final Author author3 = authorOf("3", "Исакова", "Светлана");

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AuthorRepository repository;

    @Before
    public void init() {
        mongoTemplate.getDb().drop();
        mongoTemplate.insert(author1);
        mongoTemplate.insert(author2);
        mongoTemplate.insert(author3);
    }

    @Test
    public void saveTest() {
        Mono<Author> authorMono = repository.save(authorOf("Иванов", "Иван"));

        StepVerifier
                .create(authorMono)
                .assertNext(author -> assertNotNull(author.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    public void updateTest() {
        Author expected = authorOf("1", "Кантелон", "Майк");
        Mono<Author> authorMono = repository.save(expected);

        StepVerifier
                .create(authorMono)
                .assertNext(author -> assertEquals(expected, author))
                .expectComplete()
                .verify();
    }

    @Test
    public void findByIdTest() {
        Mono<Author> authorMono = repository.findById("1");

        StepVerifier
                .create(authorMono)
                .assertNext(author -> assertEquals(author1, author))
                .expectComplete()
                .verify();
    }

    @Test
    public void findAllTest() {
        Flux<Author> authorFlux = repository.findAll();

        StepVerifier
                .create(authorFlux)
                .expectNext(author1, author2, author3)
                .expectComplete()
                .verify();
    }

    @Test
    public void countTest() {
        Mono<Long> longMono = repository.count();

        StepVerifier
                .create(longMono)
                .expectNext(3L)
                .expectComplete()
                .verify();
    }

    @Test
    public void deleteByIdTest() {
        repository.deleteById("1").subscribe();
        Flux<Author> authorFlux = repository.findAll();

        StepVerifier
                .create(authorFlux)
                .expectNext(author2, author3)
                .expectComplete()
                .verify();
    }

    @Test
    public void deleteAllTest() {
        repository.deleteAll().subscribe();
        Flux<Author> authorFlux = repository.findAll();

        StepVerifier
                .create(authorFlux)
                .expectNextCount(0L)
                .expectComplete()
                .verify();
    }
}
