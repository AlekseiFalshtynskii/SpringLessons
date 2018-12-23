package ru.spring.dao.jdbc;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.spring.dao.GenreDao;
import ru.spring.mapper.GenreMapper;
import ru.spring.model.Genre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;

@Repository
public class GenreJdbcDao implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;
    private final GenreMapper mapper;

    public GenreJdbcDao(NamedParameterJdbcOperations jdbc, GenreMapper genreMapper) {
        this.jdbc = jdbc;
        this.mapper = genreMapper;
    }

    @Override
    public Long insert(Genre genre) {
        String query = "INSERT INTO genre (name) VALUES (:name)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("name", genre.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(query, source, keyHolder, new String[]{"id"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public Long update(Genre genre) {
        String query = "UPDATE genre SET name = :name WHERE id = :id";
        Map<String, Object> params = new HashMap<>(2);
        params.put("id", genre.getId());
        params.put("name", genre.getName());
        this.jdbc.update(query, params);
        return genre.getId();
    }

    @Override
    public Genre findById(Long id) {
        String query = "SELECT * FROM genre WHERE id = :id";
        Map<String, Long> params = singletonMap("id", id);
        return this.jdbc.queryForObject(query, params, this.mapper);
    }

    @Override
    public List<Genre> findAll() {
        String query = "SELECT * FROM genre";
        return this.jdbc.query(query, this.mapper);
    }

    @Override
    public long count() {
        String query = "SELECT count(*) FROM genre";
        return this.jdbc.queryForObject(query, emptyMap(), Long.class);
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM genre WHERE id = :id";
        Map<String, Long> params = singletonMap("id", id);
        this.jdbc.update(query, params);
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM genre";
        this.jdbc.update(query, emptyMap());
    }
}
