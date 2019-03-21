package ru.spring.model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;

import javax.persistence.*;
import java.util.List;

import static org.hibernate.annotations.LazyCollectionOption.FALSE;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @ElementCollection
    @CollectionTable(name = "authorities")
    @LazyCollection(FALSE)
    private List<String> authorities;
}
