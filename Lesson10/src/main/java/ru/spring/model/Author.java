package ru.spring.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "firstname")
    private String firstName;

    public static Author authorOf(Long id, String lastName, String firstName) {
        return new Author(id, lastName, firstName);
    }

    public static Author authorOf(String lastName, String firstName) {
        return authorOf(null, lastName, firstName);
    }
}
