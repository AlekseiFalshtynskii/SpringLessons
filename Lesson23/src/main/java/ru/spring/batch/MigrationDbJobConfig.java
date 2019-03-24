package ru.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class MigrationDbJobConfig {

    @Value("${file.path.path}")
    String path;

    @Bean
    public Step deleteMigrationPathStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("deleteMigrationPathStep")
                .tasklet(new MigrationPathDeletingTasklet(new FileSystemResource(path)))
                .build();
    }

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
