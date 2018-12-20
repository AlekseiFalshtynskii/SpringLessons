package ru.spring.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.spring.service.InputOutputService;
import ru.spring.service.InputOutputServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public InputOutputService inputOutputService() {
        return new InputOutputServiceImpl(System.in, System.out);
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/bundle");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
