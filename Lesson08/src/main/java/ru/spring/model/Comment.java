package ru.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String message;
    @Column(name = "book_id")
    private Long bookId;

    public Comment() {
    }

    protected Comment(Long id, String message, Long bookId) {
        this.id = id;
        this.message = message;
        this.bookId = bookId;
    }

    public static Comment commentOf(Long id, String message, Long bookId) {
        return new Comment(id, message, bookId);
    }

    public static Comment commentOf(String message, Long bookId) {
        return commentOf(null, message, bookId);
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Long getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return "Comment(" + getId() + ", " + getMessage() + ", " + getBookId() + ")";
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
                && getBookId() == null ? other.getBookId() == null : getBookId().equals(other.getBookId());
    }

    @Override
    public int hashCode() {
        return (getId() == null ? 0 : getId().hashCode())
                + (getMessage() == null ? 0 : getMessage().hashCode())
                + (getBookId() == null ? 0 : getBookId().hashCode());
    }
}
