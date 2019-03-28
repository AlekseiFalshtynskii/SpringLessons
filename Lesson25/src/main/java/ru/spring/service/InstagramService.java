package ru.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.spring.domain.Topic;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.RandomUtils.nextBytes;
import static org.apache.commons.lang3.RandomUtils.nextInt;

@Service
@AllArgsConstructor
public class InstagramService {

    private final SubscribableChannel topicChannel;

    private final List<MessageHandler> subscribers;

    public void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(subscribeRunnable());
        executorService.submit(unSubscribeRunnable());
        executorService.submit(sendTopic());
    }

    Runnable subscribeRunnable() {
        return () -> {
            while (true) {
                int n = nextInt(10, 21);
                for (int i = 0; i < n; i++) {
                    final int q = i;
                    MessageHandler subscriber = message -> System.out.println("Подписчик " + (subscribers.size() + q) + " прочитал тему " + message.getPayload());
                    subscribers.add(subscriber);
                    topicChannel.subscribe(subscriber);
                }
                System.out.println("Подписалось " + n + " новых подписчиков");
                try {
                    TimeUnit.MILLISECONDS.sleep(nextInt(1000, 3001));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    Runnable unSubscribeRunnable() {
        return () -> {
            while (true) {
                if (subscribers.size() > 1) {
                    int n = nextInt(1, 11);
                    for (int i = 0; i < n; i++) {
                        int id = nextInt(1, subscribers.size());
                        topicChannel.unsubscribe(subscribers.get(id));
                        subscribers.remove(id);
                    }
                    System.out.println("Отписалось " + n + " подписчиков");
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(nextInt(3000, 5001));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    Runnable sendTopic() {
        return () -> {
            long id = 1;
            while (true) {
                try {
                    Topic topic = new Topic(id++, new String(nextBytes(10), "UTF-8"));
                    System.out.println("Опубликована тема " + topic);
                    topicChannel.send(MessageBuilder.withPayload(topic).build());
                    TimeUnit.MILLISECONDS.sleep(nextInt(3000, 6001));
                } catch (UnsupportedEncodingException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
