package ru.spring.dao;

import java.util.List;

public interface Dao<T, ID> {
    ID insert(T t);

    ID update(T t);

    T findById(ID id);

    List<T> findAll();

    long count();

    void deleteById(ID id);

    void deleteAll();
}
