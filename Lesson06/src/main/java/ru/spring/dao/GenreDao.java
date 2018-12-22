package ru.spring.dao;

import java.util.List;

public interface GenreDao<T, ID> extends Dao<T, ID> {
    List<T> findByBookId(ID bookId);
}
