package ru.spring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Comment {
    @Id
    private String id;
    private String message;
    private Book book;

    public static Comment commentOf(String id, String message, Book book) {
        return new Comment(id, message, book);
    }

    public static Comment commentOf(String message, Book book) {
        return commentOf(null, message, book);
    }
}
