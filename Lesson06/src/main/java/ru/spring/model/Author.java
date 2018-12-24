package ru.spring.model;

public class Author {
    private Long id;
    private String lastName;
    private String firstName;

    public Author(Long id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Author(String lastName, String firstName) {
        this(null, lastName, firstName);
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
