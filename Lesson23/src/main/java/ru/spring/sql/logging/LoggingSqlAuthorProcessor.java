package ru.spring.sql.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import ru.spring.sql.domain.Author;

public class LoggingSqlAuthorProcessor implements ItemProcessor<Author, Author> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingSqlAuthorProcessor.class);

    @Override
    public Author process(Author item) throws Exception {
        LOGGER.info("Processing Author information: {}", item);
        return item;
    }
}
