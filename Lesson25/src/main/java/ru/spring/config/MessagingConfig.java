package ru.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.spring.domain.ModerationResult;
import ru.spring.domain.Topic;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.concurrent.Executors.newCachedThreadPool;
import static org.springframework.integration.dsl.Pollers.fixedRate;

@Configuration
public class MessagingConfig {

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return fixedRate(100).get();
    }

    @Bean
    public IntegrationFlow publishing() {
        return f -> f
                .channel(c -> c.executor(newCachedThreadPool()))
                .handle("moderationService", "moderate")
                .<ModerationResult, Boolean>route(
                        ModerationResult::isAllowed,
                        mapping -> mapping
                                .subFlowMapping(TRUE, sf -> sf
                                        .channel(c -> c.queue(10))
                                        .publishSubscribeChannel(c -> c
                                                .subscribe(sub -> sub
                                                        .<ModerationResult, Topic>transform(mr ->
                                                                new Topic(
                                                                        mr.getRp().getId(),
                                                                        mr.getRp().getTags(),
                                                                        mr.getRp().getText()
                                                                )
                                                        )
                                                        .handle(m -> System.out.println("Тема опубликована: " + m.getPayload()))
                                                )
                                        )
                                )
                                .subFlowMapping(FALSE, sf -> sf
                                        .channel(c -> c.queue(10))
                                        .publishSubscribeChannel(c -> c
                                                .subscribe(sub -> sub
                                                        .transform(ModerationResult::getRp)
                                                        .handle(m -> System.out.println("В публикации отказано: " + m.getPayload()))
                                                )
                                        )
                                )
                );
    }
}
