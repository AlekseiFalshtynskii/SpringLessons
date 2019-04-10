package ru.spring.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    public static Genre genreOf(Long id, String name) {
        return new Genre(id, name);
    }

    public static Genre genreOf(String name) {
        return genreOf(null, name);
    }

    public static Genre genreOf() {
        return genreOf(null, null);
    }
}
