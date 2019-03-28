package ru.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.SubscribableChannel;

import static org.springframework.integration.dsl.MessageChannels.publishSubscribe;

@Configuration
public class MessagingConfig {

    @Bean
    public SubscribableChannel topicChannel() {
        return publishSubscribe().get();
    }
}
