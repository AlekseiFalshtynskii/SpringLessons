package ru.spring.sql.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "author")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "firstname", nullable = false)
    private String firstName;
}
