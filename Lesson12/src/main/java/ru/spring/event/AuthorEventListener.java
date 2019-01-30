package ru.spring.event;

import com.mongodb.MongoException;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import ru.spring.model.Author;
import ru.spring.model.Book;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class AuthorEventListener extends AbstractMongoEventListener<Author> {
    private final MongoOperations mongoOperations;

    public AuthorEventListener(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
        Query query = query(where("authors.$id").is(event.getSource().getObjectId("_id")));
        checkExistBook(query);
    }

    @Override
    public void onApplicationEvent(MongoMappingEvent<?> event) {
        if (event instanceof BeforeDeleteEvent
                && "author".equals(event.getCollectionName())
                && event.getDocument().isEmpty()) {
            List<Author> authors = mongoOperations.findAll(Author.class);
            Query query = query(where("authors.$id").in(authors.stream().map(Author::getId).map(ObjectId::new).collect(toList())));
            checkExistBook(query);
        }
        super.onApplicationEvent(event);
    }

    private void checkExistBook(Query query) {
        List<Book> books = mongoOperations.find(query, Book.class);
        if (!books.isEmpty()) {
            throw new MongoException("Автор(ы) не удалось удалить, т.к. существуют связанные книги");
        }
    }
}
