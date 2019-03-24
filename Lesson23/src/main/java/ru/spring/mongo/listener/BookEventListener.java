package ru.spring.mongo.listener;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.spring.mongo.domain.Book;

import java.util.Map;

@Component
@AllArgsConstructor
public class BookEventListener extends AbstractMongoEventListener<Book> {

    private final MongoOperations mongoOperations;

    private final Map<String, String> authorIds;

    private final Map<String, String> genreIds;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        Book book = event.getSource();
        book.setId(null);
        book.getAuthors().stream().forEach(author -> {
            String sqlId = author.getId();
            String mongoId = authorIds.get(sqlId);
            if (mongoId == null) {
                authorIds.put(sqlId, mongoOperations.save(author).getId());
            } else {
                author.setId(mongoId);
            }
        });
        book.getGenres().stream().forEach(genre -> {
            String sqlId = genre.getId();
            String mongoId = genreIds.get(sqlId);
            if (mongoId == null) {
                genreIds.put(sqlId, mongoOperations.save(genre).getId());
            } else {
                genre.setId(mongoId);
            }
        });
    }
}
