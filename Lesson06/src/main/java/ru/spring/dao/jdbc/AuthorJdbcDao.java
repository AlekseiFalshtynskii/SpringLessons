package ru.spring.dao.jdbc;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.spring.dao.AuthorDao;
import ru.spring.mapper.AuthorMapper;
import ru.spring.model.Author;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;

@Repository
public class AuthorJdbcDao implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;
    private final AuthorMapper mapper;

    public AuthorJdbcDao(NamedParameterJdbcOperations jdbc, AuthorMapper authorMapper) {
        this.jdbc = jdbc;
        this.mapper = authorMapper;
    }

    @Override
    public Long insert(Author author) {
        String query = "INSERT INTO author (lastname, firstname) VALUES (:lastName, :firstName)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("lastName", author.getLastName());
        source.addValue("firstName", author.getFirstName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(query, source, keyHolder, new String[]{"id"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public Long update(Author author) {
        String query = "UPDATE author SET lastname = :lastName, firstname = :firstName WHERE id = :id";
        Map<String, Object> params = new HashMap<>(3);
        params.put("id", author.getId());
        params.put("lastName", author.getLastName());
        params.put("firstName", author.getFirstName());
        this.jdbc.update(query, params);
        return author.getId();
    }

    @Override
    public Author findById(Long id) {
        String query = "SELECT * FROM author WHERE id = :id";
        Map<String, Long> params = singletonMap("id", id);
        return this.jdbc.queryForObject(query, params, this.mapper);
    }

    @Override
    public List<Author> findAll() {
        String query = "SELECT * FROM author";
        return this.jdbc.query(query, this.mapper);
    }

    @Override
    public long count() {
        String query = "SELECT count(*) FROM author";
        return this.jdbc.queryForObject(query, emptyMap(), Long.class);
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM author WHERE id = :id";
        Map<String, Long> params = singletonMap("id", id);
        this.jdbc.update(query, params);
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM author";
        this.jdbc.update(query, emptyMap());
    }
}
