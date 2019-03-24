package ru.spring.mongo.listener;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.spring.mongo.domain.Author;

@Component
public class AuthorEventListener extends AbstractMongoEventListener<Author> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Author> event) {
        event.getSource().setId(null);
        super.onBeforeConvert(event);
    }
}
