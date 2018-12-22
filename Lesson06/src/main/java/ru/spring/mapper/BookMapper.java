package ru.spring.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.spring.dao.AuthorDao;
import ru.spring.dao.GenreDao;
import ru.spring.model.Author;
import ru.spring.model.Book;
import ru.spring.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class BookMapper implements RowMapper<Book> {
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookMapper(AuthorDao authorDao, GenreDao genreDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Nullable
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer bookId = rs.getInt("id");
        List<Author> authors = authorDao.findByBookId(bookId);
        List<Genre> genres = genreDao.findByBookId(bookId);
        return new Book(
                bookId,
                rs.getString("name"),
                rs.getString("description"),
                authors,
                genres
        );
    }
}
