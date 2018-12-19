package ru.spring.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
public class AppConfig {

    @Bean
    public InputStream inputStream() {
        return System.in;
    }

    @Bean
    public PrintStream printStream() {
        return System.out;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/bundle");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
