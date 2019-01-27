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
public class Genre {
    @Id
    private String id;
    private String name;

    public static Genre genreOf(String id, String name) {
        return new Genre(id, name);
    }

    public static Genre genreOf(String name) {
        return genreOf(null, name);
    }
}
