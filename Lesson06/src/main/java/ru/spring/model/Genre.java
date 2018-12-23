package ru.spring.model;

public class Genre {
    private Long id;
    private String name;

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(String name) {
        this(null, name);
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
