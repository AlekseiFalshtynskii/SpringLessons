package ru.spring.sql.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import ru.spring.sql.domain.Genre;

public class LoggingSqlGenreProcessor implements ItemProcessor<Genre, Genre> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingSqlGenreProcessor.class);

    @Override
    public Genre process(Genre item) throws Exception {
        LOGGER.info("Processing Genre information: {}", item);
        return item;
    }
}
