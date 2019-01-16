package ru.spring.model;

import javax.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "firstname")
    private String firstName;

    public Author() {
    }

    protected Author(Long id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public static Author authorOf(Long id, String lastName, String firstName) {
        return new Author(id, lastName, firstName);
    }

    public static Author authorOf(String lastName, String firstName) {
        return authorOf(null, lastName, firstName);
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "Author(" + getId() + ", " + getLastName() + ", " + getFirstName() + ")";
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
        Author other = (Author) obj;
        return getId() == null ? other.getId() == null : getId().equals(other.getId())
                && getLastName() == null ? other.getLastName() == null : getLastName().equals(other.getLastName())
                && getFirstName() == null ? other.getFirstName() == null : getFirstName().equals(other.getFirstName());
    }

    @Override
    public int hashCode() {
        return (getId() == null ? 0 : getId().hashCode())
                + (getLastName() == null ? 0 : getLastName().hashCode())
                + (getFirstName() == null ? 0 : getFirstName().hashCode());
    }
}
