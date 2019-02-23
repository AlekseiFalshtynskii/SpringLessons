package ru.spring.testdata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.model.Author;
import ru.spring.model.Book;
import ru.spring.model.Genre;

import java.util.List;

import static java.util.Arrays.asList;
import static ru.spring.model.Author.authorOf;
import static ru.spring.model.Book.bookOf;
import static ru.spring.model.Genre.genreOf;

@Slf4j
@Configuration
public class MoveTestData {

    @Bean
    @Transactional
    public CommandLineRunner data(MongoTemplate mongoTemplate) {
        mongoTemplate.getDb().drop();

        List<Genre> genres = asList(genreOf("Программирование"));
        mongoTemplate.insertAll(genres);

        List<Author> authors = asList(
                authorOf("Уоллс", "Крейг"),
                authorOf("Жемеров", "Дмитрий"),
                authorOf("Исакова", "Светлана")
        );
        mongoTemplate.insertAll(authors);
        List<Book> books = asList(
                bookOf("Spring в действии", "", asList(authors.get(0)), genres),
                bookOf("Kotlin в действии", "", asList(authors.get(1), authors.get(2)), genres)
        );
        mongoTemplate.insertAll(books);

        return args -> {
        };
    }
}
