package ru.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;
import ru.spring.Instagram;
import ru.spring.domain.RequestPublish;
import ru.spring.domain.Topic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.RandomUtils.nextBytes;
import static org.apache.commons.lang3.RandomUtils.nextInt;

@Service
@AllArgsConstructor
public class InstagramService {

    private final Instagram instagram;

    private final SubscribableChannel topicChannel;

    public void start() throws InterruptedException {
        topicChannel.subscribe(message -> System.out.println("Темы опубликованы " + message.getPayload()));

        while (true) {
            Thread.sleep(1000);

            Collection<RequestPublish> items = generateRequestsPublish();

            System.out.println("Новые запросы на публикацию: " +
                    items.stream()
                            .map(RequestPublish::getText)
                            .collect(joining(",")));

            Collection<Topic> food = instagram.process(items);

            System.out.println("Темы смодерированы: " +
                    food.stream()
                            .map(Topic::getText)
                            .collect(joining(",")));
        }
    }

    private Collection<RequestPublish> generateRequestsPublish() {
        List<RequestPublish> items = new ArrayList<>();
        for (int i = 0; i < nextInt(1, 5); ++i) {
            items.add(generateRequestPublish());
        }
        return items;
    }

    private RequestPublish generateRequestPublish() {
        return new RequestPublish(new String(nextBytes(10)));
    }
}
