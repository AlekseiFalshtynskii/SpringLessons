package ru.spring.mongo.batch;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.spring.batch.MigrationPathDeletingTasklet;
import ru.spring.mongo.domain.Author;
import ru.spring.mongo.domain.Book;
import ru.spring.mongo.domain.Genre;
import ru.spring.mongo.logging.LoggingMongoAuthorProcessor;
import ru.spring.mongo.logging.LoggingMongoBookProcessor;
import ru.spring.mongo.logging.LoggingMongoGenreProcessor;

@Configuration
public class JsonFileToMongoDbJobConfig {

    @Value("${file.path.books}")
    String filePathBooks;

    @Value("${file.path.authors}")
    String filePathAuthors;

    @Value("${file.path.genres}")
    String filePathGenres;

    @Value("${chunk.size}")
    int chunkSize;

    @Value("${file.path.path}")
    String path;

    @Bean
    ItemReader<Book> bookFileItemReader() {
        return new JsonItemReaderBuilder<Book>()
                .name("bookFileItemReader")
                .resource(new FileSystemResource(filePathBooks))
                .jsonObjectReader(new JacksonJsonObjectReader<>(Book.class))
                .build();
    }

    @Bean
    ItemProcessor<Book, Book> bookFileItemProcessor() {
        return new LoggingMongoBookProcessor();
    }

    @Bean
    MongoItemWriter<Book> bookFileMongoDbItemWriter(MongoOperations mongoOperations) {
        return new MongoItemWriterBuilder<Book>()
                .template(mongoOperations)
                .collection("book")
                .build();
    }

    @Bean
    Step booksFileToMongoDbStep(ItemReader<Book> bookFileItemReader,
                                ItemProcessor<Book, Book> bookFileItemProcessor,
                                ItemWriter<Book> bookFileMongoDbItemWriter,
                                StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("bookFileToMongoDbStep")
                .<Book, Book>chunk(chunkSize)
                .reader(bookFileItemReader)
                .processor(bookFileItemProcessor)
                .writer(bookFileMongoDbItemWriter)
                .build();
    }

    @Bean
    ItemReader<Author> authorFileItemReader() {
        return new JsonItemReaderBuilder<Author>()
                .name("authorFileItemReader")
                .resource(new FileSystemResource(filePathAuthors))
                .jsonObjectReader(new JacksonJsonObjectReader<>(Author.class))
                .build();
    }

    @Bean
    ItemProcessor<Author, Author> authorFileItemProcessor() {
        return new LoggingMongoAuthorProcessor();
    }

    @Bean
    MongoItemWriter<Author> authorFileMongoDbItemWriter(MongoOperations mongoOperations) {
        return new MongoItemWriterBuilder<Author>()
                .template(mongoOperations)
                .collection("author")
                .build();
    }

    @Bean
    Step authorsFileToMongoDbStep(ItemReader<Author> authorFileItemReader,
                                  ItemProcessor<Author, Author> authorFileItemProcessor,
                                  ItemWriter<Author> authorFileMongoDbItemWriter,
                                  StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("authorFileToMongoDbStep")
                .<Author, Author>chunk(chunkSize)
                .reader(authorFileItemReader)
                .processor(authorFileItemProcessor)
                .writer(authorFileMongoDbItemWriter)
                .build();
    }

    @Bean
    ItemReader<Genre> genreFileItemReader() {
        return new JsonItemReaderBuilder<Genre>()
                .name("genreFileItemReader")
                .resource(new FileSystemResource(filePathGenres))
                .jsonObjectReader(new JacksonJsonObjectReader<>(Genre.class))
                .build();
    }

    @Bean
    ItemProcessor<Genre, Genre> genreFileItemProcessor() {
        return new LoggingMongoGenreProcessor();
    }

    @Bean
    MongoItemWriter<Genre> genreFileMongoDbItemWriter(MongoOperations mongoOperations) {
        return new MongoItemWriterBuilder<Genre>()
                .template(mongoOperations)
                .collection("genre")
                .build();
    }

    @Bean
    public Step deleteMigrationPathStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("deleteMigrationPathStep")
                .tasklet(new MigrationPathDeletingTasklet(new FileSystemResource(path)))
                .build();
    }

    @Bean
    Step genresFileToMongoDbStep(ItemReader<Genre> genreFileItemReader,
                                 ItemProcessor<Genre, Genre> genreFileItemProcessor,
                                 ItemWriter<Genre> genreFileMongoDbItemWriter,
                                 StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("genreFileToMongoDbStep")
                .<Genre, Genre>chunk(chunkSize)
                .reader(genreFileItemReader)
                .processor(genreFileItemProcessor)
                .writer(genreFileMongoDbItemWriter)
                .build();
    }
}
