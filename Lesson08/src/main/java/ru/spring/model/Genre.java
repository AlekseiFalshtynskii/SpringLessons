package ru.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Genre {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Genre() {
    }

    protected Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Genre genreOf(Long id, String name) {
        return new Genre(id, name);
    }

    public static Genre genreOf(String name) {
        return genreOf(null, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Genre(" + getId() + ", " + getName() + ")";
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
        Genre other = (Genre) obj;
        return getId() == null ? other.getId() == null : getId().equals(other.getId())
                && getName() == null ? other.getName() == null : getName().equals(other.getName());
    }

    @Override
    public int hashCode() {
        return (getId() == null ? 0 : getId().hashCode())
                + (getName() == null ? 0 : getName().hashCode());
    }
}
