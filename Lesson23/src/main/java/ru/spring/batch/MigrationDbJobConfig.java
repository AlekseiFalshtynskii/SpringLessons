package ru.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigrationDbJobConfig {

    @Bean
    Job migrationDbJob(JobBuilderFactory jobBuilderFactory,
                       Step booksToJsonFileStep,
                       Step authorsToJsonFileStep,
                       Step genresToJsonFileStep,
                       Step booksFileToMongoDbStep,
                       Step authorsFileToMongoDbStep,
                       Step genresFileToMongoDbStep,
                       Step deleteMigrationPathStep) {
        return jobBuilderFactory.get("migrationDbJob")
                .incrementer(new RunIdIncrementer())
                .flow(booksToJsonFileStep)
                .next(authorsToJsonFileStep)
                .next(genresToJsonFileStep)
                .next(booksFileToMongoDbStep)
                .next(authorsFileToMongoDbStep)
                .next(genresFileToMongoDbStep)
                .next(deleteMigrationPathStep)
                .end()
                .build();
    }
}
