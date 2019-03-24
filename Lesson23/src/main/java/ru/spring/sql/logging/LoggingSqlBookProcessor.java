package ru.spring.sql.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import ru.spring.sql.domain.Book;

public class LoggingSqlBookProcessor implements ItemProcessor<Book, Book> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingSqlBookProcessor.class);

    @Override
    public Book process(Book item) throws Exception {
        LOGGER.info("Processing Book information: {}", item);
        return item;
    }
}
