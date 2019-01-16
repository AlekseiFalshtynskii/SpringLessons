package ru.spring.model;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    public Comment() {
    }

    protected Comment(Long id, String message, Book book) {
        this.id = id;
        this.message = message;
        this.book = book;
    }

    public static Comment commentOf(Long id, String message, Book book) {
        return new Comment(id, message, book);
    }

    public static Comment commentOf(String message, Book book) {
        return commentOf(null, message, book);
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "Comment(" + getId() + ", " + getMessage() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Comment other = (Comment) obj;
        return getId() == null ? other.getId() == null : getId().equals(other.getId())
                && getMessage() == null ? other.getMessage() == null : getMessage().equals(other.getMessage())
                && getBook() == null ? other.getBook() == null : getBook().equals(other.getBook());
    }

    @Override
    public int hashCode() {
        return (getId() == null ? 0 : getId().hashCode())
                + (getMessage() == null ? 0 : getMessage().hashCode())
                + (getBook() == null ? 0 : getBook().hashCode());
    }
}
