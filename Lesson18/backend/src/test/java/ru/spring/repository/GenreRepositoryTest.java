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
import ru.spring.model.Genre;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ru.spring.model.Genre.genreOf;

@RunWith(SpringRunner.class)
@DataMongoTest
public class GenreRepositoryTest {
    private static final Genre genre1 = genreOf("1", "Программирование");

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private GenreRepository repository;

    @Before
    public void init() {
        mongoTemplate.getDb().drop();
        mongoTemplate.insert(genre1);
    }

    @Test
    public void saveTest() {
        Mono<Genre> genreMono = repository.save(genreOf("Жанр"));

        StepVerifier
                .create(genreMono)
                .assertNext(genre -> assertNotNull(genre.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    public void updateTest() {
        Genre expected = genreOf("1", "Фантастика");
        Mono<Genre> genreMono = repository.save(expected);

        StepVerifier
                .create(genreMono)
                .assertNext(genre -> assertEquals(expected, genre))
                .expectComplete()
                .verify();
    }

    @Test
    public void findByIdTest() {
        Mono<Genre> genreMono = repository.findById("1");

        StepVerifier
                .create(genreMono)
                .assertNext(genre -> assertEquals(genre1, genre))
                .expectComplete()
                .verify();
    }

    @Test
    public void findAllTest() {
        Flux<Genre> genreFlux = repository.findAll();

        StepVerifier
                .create(genreFlux)
                .expectNext(genre1)
                .expectComplete()
                .verify();
    }

    @Test
    public void countTest() {
        Mono<Long> longMono = repository.count();

        StepVerifier
                .create(longMono)
                .expectNext(1L)
                .expectComplete()
                .verify();
    }

    @Test
    public void deleteByIdTest() {
        repository.deleteById("1").subscribe();
        Flux<Genre> genreFlux = repository.findAll();

        StepVerifier
                .create(genreFlux)
                .expectNextCount(0L)
                .expectComplete()
                .verify();
    }

    @Test
    public void deleteAllTest() {
        repository.deleteAll().subscribe();
        Flux<Genre> genreFlux = repository.findAll();

        StepVerifier
                .create(genreFlux)
                .expectNextCount(0L)
                .expectComplete()
                .verify();
    }
}
