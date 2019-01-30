package ru.spring.event;

import com.mongodb.MongoException;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import ru.spring.model.Book;
import ru.spring.model.Genre;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class GenreEventListener extends AbstractMongoEventListener<Genre> {
    private final MongoOperations mongoOperations;

    public GenreEventListener(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Genre> event) {
        Query query = query(where("genres.$id").is(event.getSource().getObjectId("_id")));
        checkExistBook(query);
    }

    @Override
    public void onApplicationEvent(MongoMappingEvent<?> event) {
        if (event instanceof BeforeDeleteEvent
                && "genre".equals(event.getCollectionName())
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
            throw new MongoException("Жанр(ы) не удалось удалить, т.к. существуют связанные книги");
        }
    }
}
