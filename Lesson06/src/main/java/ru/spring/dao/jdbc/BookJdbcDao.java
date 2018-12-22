package ru.spring.dao.jdbc;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.spring.dao.BookDao;
import ru.spring.mapper.BookMapper;
import ru.spring.model.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static java.util.Objects.isNull;

@Repository
public class BookJdbcDao implements BookDao<Book, Integer> {
    private final NamedParameterJdbcOperations jdbc;
    private final BookMapper mapper;
    private final AuthorJdbcDao authorDao;
    private final GenreJdbcDao genreDao;

    public BookJdbcDao(NamedParameterJdbcOperations jdbc,
                       BookMapper bookMapper,
                       AuthorJdbcDao authorDao,
                       GenreJdbcDao genreDao) {
        this.jdbc = jdbc;
        this.mapper = bookMapper;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public Integer insert(Book book) {
        String query = "INSERT INTO book (name, description) VALUES (:name, :description)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("name", book.getName());
        source.addValue("description", book.getDescription());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(query, source, keyHolder, new String[]{"id"});
        Integer id = keyHolder.getKey().intValue();
        bindBookAuthorAndGenre(book, id);
        return id;
    }

    @Override
    public Integer update(Book book) {
        String query = "UPDATE book SET name = :name, description = :description WHERE id = :id";
        Map<String, Object> params = new HashMap<>(3);
        params.put("id", book.getId());
        params.put("name", book.getName());
        params.put("description", book.getDescription());
        jdbc.update(query, params);
        bindBookAuthorAndGenre(book, book.getId());
        return book.getId();
    }

    @Override
    public Book findById(Integer id) {
        String query = "SELECT * FROM book WHERE id = :id";
        Map<String, Integer> params = singletonMap("id", id);
        return jdbc.queryForObject(query, params, mapper);
    }

    @Override
    public List<Book> findAll() {
        String query = "SELECT * FROM book";
        return jdbc.query(query, mapper);
    }

    @Override
    public long count() {
        String query = "SELECT count(*) FROM book";
        return jdbc.queryForObject(query, emptyMap(), Long.class);
    }

    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM book WHERE id = :id";
        Map<String, Integer> params = singletonMap("id", id);
        jdbc.update(query, params);
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM book";
        jdbc.update(query, emptyMap());
    }

    private void bindBookAuthorAndGenre(Book book, Integer bookId) {
        if (!isNull(book.getAuthors())) {
            book.getAuthors().forEach(author -> {
                Integer authorId;
                if (author.getId() == null) {
                    authorId = authorDao.insert(author);
                } else {
                    authorId = authorDao.update(author);
                }
                bindBookAuthor(bookId, authorId);
            });
        }
        if (!isNull(book.getGenres())) {
            book.getGenres().forEach(genre -> {
                Integer genreId;
                if (genre.getId() == null) {
                    genreId = genreDao.insert(genre);
                } else {
                    genreId = genreDao.update(genre);
                }
                bindBookGenre(bookId, genreId);
            });
        }
    }

    private void bindBookAuthor(Integer bookId, Integer authorId) {
        if (countBookAuthor(bookId, authorId) == 0) {
            String query = "INSERT INTO book_author (book_id, author_id) VALUES (:bookId, :authorId)";
            Map<String, Integer> params = new HashMap<>(2);
            params.put("bookId", bookId);
            params.put("authorId", authorId);
            jdbc.update(query, params);
        }
    }

    private long countBookAuthor(Integer bookId, Integer authorId) {
        String query = "SELECT count(*) FROM book_author WHERE book_id = :bookId AND author_id = :authorId";
        Map<String, Integer> params = new HashMap<>(2);
        params.put("bookId", bookId);
        params.put("authorId", authorId);
        return jdbc.queryForObject(query, params, Long.class);
    }

    private void bindBookGenre(Integer bookId, Integer genreId) {
        if (countBookGenre(bookId, genreId) == 0) {
            String query = "INSERT INTO book_genre (book_id, genre_id) VALUES (:bookId, :genreId)";
            Map<String, Integer> params = new HashMap<>(2);
            params.put("bookId", bookId);
            params.put("genreId", genreId);
            jdbc.update(query, params);
        }
    }

    private long countBookGenre(Integer bookId, Integer genreId) {
        String query = "SELECT count(*) FROM book_genre WHERE book_id = :bookId AND genre_id = :genreId";
        Map<String, Integer> params = new HashMap<>(2);
        params.put("bookId", bookId);
        params.put("genreId", genreId);
        return jdbc.queryForObject(query, params, Long.class);
    }
}
