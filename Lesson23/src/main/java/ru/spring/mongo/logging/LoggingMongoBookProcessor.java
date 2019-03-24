package ru.spring.mongo.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import ru.spring.mongo.domain.Book;

public class LoggingMongoBookProcessor implements ItemProcessor<Book, Book> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingMongoBookProcessor.class);

    @Override
    public Book process(Book item) throws Exception {
        LOGGER.info("Processing Book information: {}", item);
        return item;
    }
}
