package ru.spring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static ru.spring.model.Book.BOOK_COLLECTION_NAME;

@Document(collection = BOOK_COLLECTION_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Book {
    public static final String BOOK_COLLECTION_NAME = "book";

    @Id
    private String id;
    private String name;
    private String description;
    @DBRef
    private List<Author> authors;
    @DBRef
    private List<Genre> genres;

    public static Book bookOf(String id, String name, String description, List<Author> authors, List<Genre> genres) {
        return new Book(id, name, description, authors, genres);
    }

    public static Book bookOf(String name, String description, List<Author> authors, List<Genre> genres) {
        return bookOf(null, name, description, authors, genres);
    }
}
