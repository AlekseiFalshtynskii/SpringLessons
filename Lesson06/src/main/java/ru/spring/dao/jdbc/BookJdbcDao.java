package ru.spring.dao.jdbc;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.spring.dao.AuthorDao;
import ru.spring.dao.BookDao;
import ru.spring.dao.GenreDao;
import ru.spring.mapper.BookSetExtractor;
import ru.spring.model.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static java.util.Objects.isNull;

@Repository
public class BookJdbcDao implements BookDao {
    private final NamedParameterJdbcOperations jdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookJdbcDao(NamedParameterJdbcOperations jdbc,
                       AuthorDao authorDao,
                       GenreDao genreDao) {
        this.jdbc = jdbc;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public Long insert(Book book) {
        String query = "INSERT INTO book (name, description) VALUES (:name, :description)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("name", book.getName());
        source.addValue("description", book.getDescription());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(query, source, keyHolder, new String[]{"id"});
        Long id = keyHolder.getKey().longValue();
        bindBookAuthorAndGenre(book, id);
        return id;
    }

    @Override
    public Long update(Book book) {
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
    public Book findById(Long id) {
        String query = "SELECT book.id as id, book.name as name, book.description as description, " +
                "   author.id as author_id, author.lastname as lastname, author.firstname as firstname, " +
                "   genre.id as genre_id, genre.name as genre_name FROM book " +
                "JOIN book_author ON book_author.book_id = book.id " +
                "JOIN author ON author.id = book_author.author_id " +
                "JOIN book_genre ON book_genre.book_id = book.id " +
                "JOIN genre ON genre.id = book_genre.genre_id " +
                "WHERE book.id = :id";
        Map<String, Long> params = singletonMap("id", id);
        List<Book> books = jdbc.query(query, params, new BookSetExtractor());
        return books.isEmpty() ? null : books.get(0);
    }

    @Override
    public List<Book> findAll() {
        String query = "SELECT book.id as id, book.name as name, book.description as description, " +
                "   author.id as author_id, author.lastname as lastname, author.firstname as firstname, " +
                "   genre.id as genre_id, genre.name as genre_name FROM book " +
                "JOIN book_author ON book_author.book_id = book.id " +
                "JOIN author ON author.id = book_author.author_id " +
                "JOIN book_genre ON book_genre.book_id = book.id " +
                "JOIN genre ON genre.id = book_genre.genre_id";
        return jdbc.query(query, new BookSetExtractor());
    }

    @Override
    public long count() {
        String query = "SELECT count(*) FROM book";
        return jdbc.queryForObject(query, emptyMap(), Long.class);
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM book WHERE id = :id";
        Map<String, Long> params = singletonMap("id", id);
        jdbc.update(query, params);
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM book";
        jdbc.update(query, emptyMap());
    }

    private void bindBookAuthorAndGenre(Book book, Long bookId) {
        if (!isNull(book.getAuthors())) {
            book.getAuthors().forEach(author -> {
                Long authorId;
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
                Long genreId;
                if (genre.getId() == null) {
                    genreId = genreDao.insert(genre);
                } else {
                    genreId = genreDao.update(genre);
                }
                bindBookGenre(bookId, genreId);
            });
        }
    }

    private void bindBookAuthor(Long bookId, Long authorId) {
        if (countBookAuthor(bookId, authorId) == 0) {
            String query = "INSERT INTO book_author (book_id, author_id) VALUES (:bookId, :authorId)";
            Map<String, Long> params = new HashMap<>(2);
            params.put("bookId", bookId);
            params.put("authorId", authorId);
            jdbc.update(query, params);
        }
    }

    private long countBookAuthor(Long bookId, Long authorId) {
        String query = "SELECT count(*) FROM book_author WHERE book_id = :bookId AND author_id = :authorId";
        Map<String, Long> params = new HashMap<>(2);
        params.put("bookId", bookId);
        params.put("authorId", authorId);
        return jdbc.queryForObject(query, params, Long.class);
    }

    private void bindBookGenre(Long bookId, Long genreId) {
        if (countBookGenre(bookId, genreId) == 0) {
            String query = "INSERT INTO book_genre (book_id, genre_id) VALUES (:bookId, :genreId)";
            Map<String, Long> params = new HashMap<>(2);
            params.put("bookId", bookId);
            params.put("genreId", genreId);
            jdbc.update(query, params);
        }
    }

    private long countBookGenre(Long bookId, Long genreId) {
        String query = "SELECT count(*) FROM book_genre WHERE book_id = :bookId AND genre_id = :genreId";
        Map<String, Long> params = new HashMap<>(2);
        params.put("bookId", bookId);
        params.put("genreId", genreId);
        return jdbc.queryForObject(query, params, Long.class);
    }
}
