package ru.spring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import static ru.spring.model.Comment.COMMENT_COLLECTION_NAME;

@Document(collection = COMMENT_COLLECTION_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Comment {
    public static final String COMMENT_COLLECTION_NAME = "comment";

    @Id
    private String id;

    @NonNull
    private String message;

    @NonNull
    @DBRef
    private Book book;

    public static Comment commentOf(String id, String message, Book book) {
        return new Comment(id, message, book);
    }

    public static Comment commentOf(String message, Book book) {
        return commentOf(null, message, book);
    }
}
