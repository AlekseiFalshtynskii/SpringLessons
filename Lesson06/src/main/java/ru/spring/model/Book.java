package ru.spring.model;

import java.util.List;

public class Book {
    private Integer id;
    private String name;
    private String description;
    private List<Author> authors;
    private List<Genre> genres;

    public Book(Integer id, String name, String description, List<Author> authors, List<Genre> genres) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.authors = authors;
        this.genres = genres;
    }

    public Book(String name, String description, List<Author> authors, List<Genre> genres) {
        this(null, name, description, authors, genres);
    }

    public Integer getId() {
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
        return "Book(" + getId() + ", " + getName() + ", " + getDescription() + ", (" + getAuthors() + "), (" + getGenres() + "))";
    }
}
