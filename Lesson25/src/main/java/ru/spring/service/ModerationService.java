package ru.spring.service;

import org.springframework.stereotype.Service;
import ru.spring.domain.RequestPublish;
import ru.spring.domain.Topic;

@Service
public class ModerationService {

    public Topic moderate(RequestPublish rp) throws Exception {
        System.out.println("Модерация запроса " + rp.getText());
        Thread.sleep(3000);
        System.out.println("Модерация запроса " + rp.getText() + " завершена");
        return new Topic(rp.getText());
    }
}
