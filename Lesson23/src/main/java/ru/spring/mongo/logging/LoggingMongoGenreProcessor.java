package ru.spring.mongo.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import ru.spring.mongo.domain.Genre;

public class LoggingMongoGenreProcessor implements ItemProcessor<Genre, Genre> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingMongoGenreProcessor.class);

    @Override
    public Genre process(Genre item) throws Exception {
        LOGGER.info("Processing Genre information: {}", item);
        return item;
    }
}
