package ru.spring;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class Lesson23Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson23Application.class, args);
    }

}
