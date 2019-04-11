package ru.spring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.spring.model.Genre;

@RepositoryRestResource(path = "/genres")
public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {
}
