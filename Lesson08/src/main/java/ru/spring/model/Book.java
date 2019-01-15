package ru.spring.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    public Book() {
    }

    protected Book(Long id, String name, String description, List<Author> authors, List<Genre> genres) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.authors = authors;
        this.genres = genres;
    }

    public static Book bookOf(Long id, String name, String description, List<Author> authors, List<Genre> genres) {
        return new Book(id, name, description, authors, genres);
    }

    public static Book bookOf(String name, String description, List<Author> authors, List<Genre> genres) {
        return bookOf(null, name, description, authors, genres);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "Book(" + getId() + ", " + getName() + ", " + getDescription() + ", (" + getAuthors() + "), ("
                + getGenres() + "))";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Book other = (Book) obj;
        return getId() == null ? other.getId() == null : getId().equals(other.getId())
                && getName() == null ? other.getName() == null : getName().equals(other.getName())
                && getDescription() == null ? other.getDescription() == null : getDescription().equals(other.getDescription())
                && getAuthors() == null ? other.getAuthors() == null : getAuthors().equals(other.getAuthors())
                && getGenres() == null ? other.getGenres() == null : getGenres().equals(other.getGenres());
    }

    @Override
    public int hashCode() {
        int hash = (getId() == null ? 0 : getId().hashCode())
                + (getName() == null ? 0 : getName().hashCode())
                + (getDescription() == null ? 0 : getDescription().hashCode());
        for (Author author : getAuthors()) {
            hash += author.hashCode();
        }
        for (Genre genre : getGenres()) {
            hash += genre.hashCode();
        }
        return hash;
    }
}
