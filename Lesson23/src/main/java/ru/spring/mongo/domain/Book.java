package ru.spring.mongo.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Book {

    @Id
    private String id;

    @NonNull
    private String name;

    private String description;

    @NonNull
    @DBRef
    private List<Author> authors;

    @NonNull
    @DBRef
    private List<Genre> genres;
}
