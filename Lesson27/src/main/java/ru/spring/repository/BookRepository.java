package ru.spring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.spring.model.Book;

@RepositoryRestResource(path = "/books")
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
}
