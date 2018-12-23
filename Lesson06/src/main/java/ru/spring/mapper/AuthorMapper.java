package ru.spring.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.spring.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthorMapper implements RowMapper<Author> {

    @Nullable
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Author(rs.getLong("id"), rs.getString("lastname"), rs.getString("firstname"));
    }
}
