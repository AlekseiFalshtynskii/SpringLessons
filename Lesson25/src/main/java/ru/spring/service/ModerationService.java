package ru.spring.service;

import org.springframework.stereotype.Service;
import ru.spring.domain.ModerationResult;
import ru.spring.domain.RequestPublish;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.RandomUtils.nextInt;

@Service
public class ModerationService {

    static List<String> allTags = asList(
            "добро",
            "солнышко",
            "котики",
            "птички",
            "жучки",
            "паучки",
            "чебурашки",
            "программирование",
            "клубничка",
            "ненависть",
            "жесть"
    );

    private static List<String> badTags = asList(
            "клубничка",
            "ненависть",
            "жесть"
    );

    public ModerationResult moderate(RequestPublish rp) throws Exception {
        System.out.println("Модерация запроса " + rp);
        TimeUnit.MILLISECONDS.sleep(nextInt(999, 3001));
        System.out.println("Модерация запроса " + rp + " завершена");
        return new ModerationResult(
                isAllowed(rp),
                rp
        );
    }

    private boolean isAllowed(RequestPublish rp) {
        return rp.getTags().parallelStream().filter(badTags::contains).count() == 0;
    }
}
