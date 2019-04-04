package ru.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.spring.Pikabu;
import ru.spring.domain.RequestPublish;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static ru.spring.service.ModerationService.allTags;

@Service
@RequiredArgsConstructor
public class PikabuService {

    private final Pikabu pikabu;

    private AtomicLong atomicLong = new AtomicLong();

    public void start() throws InterruptedException {
        ExecutorService executorService = newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.submit(runPublishTopics());
        }
    }

    private Runnable runPublishTopics() {
        return () -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(nextInt(999, 3001));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pikabu.process(generateRequestPublish());
            }
        };
    }

    private RequestPublish generateRequestPublish() {
        return new RequestPublish(
                atomicLong.incrementAndGet(),
                randomTags(),
                "Какой-то текст");
    }

    private List<String> randomTags() {
        List<String> tags = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            tags.add(allTags.get(nextInt(0, allTags.size())));
        }
        return tags;
    }
}
