package ru.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;

import static org.springframework.integration.dsl.IntegrationFlows.from;
import static org.springframework.integration.dsl.MessageChannels.publishSubscribe;
import static org.springframework.integration.dsl.MessageChannels.queue;

@Configuration
public class MessagingConfig {

    @Bean
    public QueueChannel moderationChannel() {
        return queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel topicChannel() {
        return publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow topicFlow() {
        return from("moderationChannel")
                .split()
                .handle("moderationService", "moderate")
                .aggregate()
                .channel("topicChannel")
                .get();
    }
}
