package ru.spring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static ru.spring.model.Author.AUTHOR_COLLECTION_NAME;

@Document(collection = AUTHOR_COLLECTION_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Author {
    public static final String AUTHOR_COLLECTION_NAME = "author";

    @Id
    private String id;

    @NonNull
    private String lastName;

    @NonNull
    private String firstName;

    public static Author authorOf(String id, String lastName, String firstName) {
        return new Author(id, lastName, firstName);
    }

    public static Author authorOf(String lastName, String firstName) {
        return authorOf(null, lastName, firstName);
    }

    public static Author authorOf() {
        return authorOf(null, null, null);
    }
}
