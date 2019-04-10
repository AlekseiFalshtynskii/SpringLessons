package ru.spring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.spring.model.Author;

@RepositoryRestResource(path = "/authors")
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {
}
