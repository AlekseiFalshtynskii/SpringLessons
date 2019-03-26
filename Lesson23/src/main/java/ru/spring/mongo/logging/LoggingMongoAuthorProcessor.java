package ru.spring.mongo.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import ru.spring.mongo.domain.Author;

public class LoggingMongoAuthorProcessor implements ItemProcessor<Author, Author> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingMongoAuthorProcessor.class);

    @Override
    public Author process(Author item) throws Exception {
        LOGGER.info("Processing Author information: {}", item);
        return item;
    }
}
