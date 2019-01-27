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
public class Author {
    @Id
    private String id;
    private String lastName;
    private String firstName;

    public static Author authorOf(String id, String lastName, String firstName) {
        return new Author(id, lastName, firstName);
    }

    public static Author authorOf(String lastName, String firstName) {
        return authorOf(null, lastName, firstName);
    }
}
