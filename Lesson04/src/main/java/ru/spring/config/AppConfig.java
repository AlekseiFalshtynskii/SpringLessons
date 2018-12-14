package ru.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

@Configuration
public class AppConfig {
    @Value("${quiz.locale}")
    private String locale;
    @Value("${quiz.questions.ru.csv}")
    private String questionsRU;
    @Value("${quiz.questions.en.csv}")
    private String questionsEN;

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public PrintStream printStream() {
        return System.out;
    }

    @Bean
    public Locale locale() {
        Locale locale = new Locale(this.locale);
        LocaleContextHolder.setDefaultLocale(locale);
        return LocaleContextHolder.getLocale();
    }

    @Bean
    public Resource resource(Locale locale) {
        if ("ru_RU".equalsIgnoreCase(locale.getDisplayName())) {
            return new ClassPathResource(this.questionsRU);
        }
        return new ClassPathResource(this.questionsEN);
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/bundle");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
