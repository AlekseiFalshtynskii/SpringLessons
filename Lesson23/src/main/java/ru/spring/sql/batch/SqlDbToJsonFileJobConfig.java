package ru.spring.sql.batch;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import ru.spring.sql.domain.Author;
import ru.spring.sql.domain.Book;
import ru.spring.sql.domain.Genre;
import ru.spring.sql.logging.LoggingSqlAuthorProcessor;
import ru.spring.sql.logging.LoggingSqlBookProcessor;
import ru.spring.sql.logging.LoggingSqlGenreProcessor;

import javax.persistence.EntityManagerFactory;

@Configuration
public class SqlDbToJsonFileJobConfig {

    private static final String QUERY_FIND_BOOKS = "SELECT book FROM Book book";

    private static final String QUERY_FIND_AUTHORS = "SELECT author FROM Author author WHERE NOT EXISTS (SELECT 1 FROM Book book WHERE author MEMBER OF book.authors)";

    private static final String QUERY_FIND_GENRES = "SELECT genre FROM Genre genre WHERE NOT EXISTS (SELECT 1 FROM Book book WHERE genre MEMBER OF book.genres)";

    @Value("${file.path.books}")
    String filePathBooks;

    @Value("${file.path.authors}")
    String filePathAuthors;

    @Value("${file.path.genres}")
    String filePathGenres;

    @Value("${chunk.size}")
    int chunkSize;

    @Bean
    JpaPagingItemReader bookItemReader(EntityManagerFactory entityManagerFactory) {
        return new JpaPagingItemReaderBuilder()
                .name("bookItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(QUERY_FIND_BOOKS)
                .pageSize(chunkSize)
                .build();
    }

    @Bean
    ItemProcessor<Book, Book> bookItemProcessor() {
        return new LoggingSqlBookProcessor();
    }

    @Bean
    ItemWriter<Book> bookItemWriter() {
        return new JsonFileItemWriter<>(
                new FileSystemResource(filePathBooks),
                new JacksonJsonObjectMarshaller<>()
        );
    }

    @Bean
    Step booksToJsonFileStep(ItemReader<Book> bookItemReader,
                             ItemProcessor<Book, Book> bookItemProcessor,
                             ItemWriter<Book> bookItemWriter,
                             StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("booksToJsonFileStep")
                .<Book, Book>chunk(chunkSize)
                .reader(bookItemReader)
                .processor(bookItemProcessor)
                .writer(bookItemWriter)
                .build();
    }

    @Bean
    JpaPagingItemReader authorItemReader(EntityManagerFactory entityManagerFactory) {
        return new JpaPagingItemReaderBuilder()
                .name("authorItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(QUERY_FIND_AUTHORS)
                .pageSize(chunkSize)
                .build();
    }

    @Bean
    ItemProcessor<Author, Author> authorItemProcessor() {
        return new LoggingSqlAuthorProcessor();
    }

    @Bean
    ItemWriter<Author> authorItemWriter() {
        return new JsonFileItemWriter<>(
                new FileSystemResource(filePathAuthors),
                new JacksonJsonObjectMarshaller<>()
        );
    }

    @Bean
    Step authorsToJsonFileStep(ItemReader<Author> authorItemReader,
                               ItemProcessor<Author, Author> authorItemProcessor,
                               ItemWriter<Author> authorItemWriter,
                               StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("authorsToJsonFileStep")
                .<Author, Author>chunk(chunkSize)
                .reader(authorItemReader)
                .processor(authorItemProcessor)
                .writer(authorItemWriter)
                .build();
    }

    @Bean
    JpaPagingItemReader genreItemReader(EntityManagerFactory entityManagerFactory) {
        return new JpaPagingItemReaderBuilder()
                .name("genreItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(QUERY_FIND_GENRES)
                .pageSize(chunkSize)
                .build();
    }

    @Bean
    ItemProcessor<Genre, Genre> genreItemProcessor() {
        return new LoggingSqlGenreProcessor();
    }

    @Bean
    ItemWriter<Genre> genreItemWriter() {
        return new JsonFileItemWriter<>(
                new FileSystemResource(filePathGenres),
                new JacksonJsonObjectMarshaller<>()
        );
    }

    @Bean
    Step genresToJsonFileStep(ItemReader<Genre> genreItemReader,
                              ItemProcessor<Genre, Genre> genreItemProcessor,
                              ItemWriter<Genre> genreItemWriter,
                              StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("genresToJsonFileStep")
                .<Genre, Genre>chunk(chunkSize)
                .reader(genreItemReader)
                .processor(genreItemProcessor)
                .writer(genreItemWriter)
                .build();
    }
}
