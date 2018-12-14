package ru.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import ru.spring.csv.CSVReader;
import ru.spring.csv.CSVReaderImpl;

@Configuration
public class CSVReaderImplTestConfig {
    @Value("${quiz.questions.ru.csv}")
    private String questionsRU;
    @Value("${quiz.questions.en.csv}")
    private String questionsEN;

    @Bean
    public CSVReader csvReaderRU() {
        return new CSVReaderImpl(new ClassPathResource(this.questionsRU));
    }

    @Bean
    public CSVReader csvReaderEN() {
        return new CSVReaderImpl(new ClassPathResource(this.questionsEN));
    }
}
