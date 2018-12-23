package ru.spring.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.Nullable;
import ru.spring.model.Author;
import ru.spring.model.Book;
import ru.spring.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookSetExtractor implements ResultSetExtractor<List<Book>> {
    private Map<Long, Book> books = new HashMap<>();

    @Nullable
    @Override
    public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
        while (rs.next()) {
            Long id = rs.getLong("id");
            Book book = books.get(id);
            if (book == null) {
                book = new Book(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        new ArrayList<>(),
                        new ArrayList<>()
                );
            }
            Author author = new Author(
                    rs.getLong("author_id"),
                    rs.getString("lastname"),
                    rs.getString("firstname")
            );
            if (!book.getAuthors().contains(author)) {
                book.getAuthors().add(author);
            }
            Genre genre = new Genre(
                    rs.getLong("genre_id"),
                    rs.getString("genre_name")
            );
            if (!book.getGenres().contains(genre)) {
                book.getGenres().add(genre);
            }
            books.put(id, book);
        }
        return new ArrayList<>(books.values());
    }
}
