package ru.spring.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public static Comment commentOf(Long id, String message, Book book) {
        return new Comment(id, message, book);
    }

    public static Comment commentOf(String message, Book book) {
        return commentOf(null, message, book);
    }
}
