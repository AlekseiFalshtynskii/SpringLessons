package ru.spring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static ru.spring.model.Genre.GENRE_COLLECTION_NAME;

@Document(collection = GENRE_COLLECTION_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Genre {
    public static final String GENRE_COLLECTION_NAME = "genre";

    @Id
    private String id;

    @NonNull
    private String name;

    public static Genre genreOf(String id, String name) {
        return new Genre(id, name);
    }

    public static Genre genreOf(String name) {
        return genreOf(null, name);
    }

    public static Genre genreOf() {
        return genreOf(null, null);
    }
}
