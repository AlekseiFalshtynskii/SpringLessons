package ru.spring.mongo.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "author")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Author {

    @Id
    private String id;

    @NonNull
    private String lastName;

    @NonNull
    private String firstName;
}
