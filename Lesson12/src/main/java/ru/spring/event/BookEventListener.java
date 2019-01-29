package ru.spring.event;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import ru.spring.model.Author;
import ru.spring.model.Book;
import ru.spring.model.Comment;
import ru.spring.model.Genre;

import java.util.Collection;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class BookEventListener extends AbstractMongoEventListener<Book> {
    private final MongoOperations mongoOperations;

    public BookEventListener(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        Book book = event.getSource();
        if (book.getAuthors() != null) {
            book.setAuthors((List) mongoOperations.insert((Collection) book.getAuthors(), Author.class));
        }
        if (book.getGenres() != null) {
            book.setGenres((List) mongoOperations.insert((Collection) book.getGenres(), Genre.class));
        }
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        Query query = query(where("book.id").is(event.getSource().getObjectId("_id").toHexString()));
        mongoOperations.remove(query, Comment.class);
    }
}
