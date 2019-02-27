package ru.spring.event;

import com.mongodb.MongoException;
import org.bson.types.ObjectId;
import org.springframework.context.MessageSource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import ru.spring.model.Book;
import ru.spring.model.Genre;

import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static ru.spring.model.Genre.GENRE_COLLECTION_NAME;

@Component
public class GenreEventListener extends AbstractMongoEventListener<Genre> {
    private final MongoOperations mongoOperations;
    private final MessageSource messageSource;

    public GenreEventListener(MongoOperations mongoOperations,
                              MessageSource messageSource) {
        this.mongoOperations = mongoOperations;
        this.messageSource = messageSource;
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Genre> event) {
        Query query = query(where("genres.$id").is(event.getSource().getObjectId("_id")));
        checkExistBook(query);
    }

    @Override
    public void onApplicationEvent(MongoMappingEvent<?> event) {
        if (event instanceof BeforeDeleteEvent
                && GENRE_COLLECTION_NAME.equals(event.getCollectionName())
                && event.getDocument().isEmpty()) {
            List<Genre> genres = mongoOperations.findAll(Genre.class);
            Query query = query(where("genres.$id").in(genres.stream().map(Genre::getId).map(ObjectId::new).collect(toList())));
            checkExistBook(query);
        }
        super.onApplicationEvent(event);
    }

    private void checkExistBook(Query query) {
        List<Book> books = mongoOperations.find(query, Book.class);
        if (!books.isEmpty()) {
            throw new MongoException(messageSource.getMessage("genres.delete.error", new Object[]{}, Locale.getDefault()));
        }
    }
}
