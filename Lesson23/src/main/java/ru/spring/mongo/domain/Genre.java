package ru.spring.mongo.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Genre {

    @Id
    private String id;

    @NonNull
    private String name;
}
