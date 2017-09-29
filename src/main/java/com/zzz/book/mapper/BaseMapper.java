package com.zzz.book.mapper;

import java.util.List;

public interface BaseMapper<T> {

    List<T> findAll();

    T findById(long id);

    int save(T t);

    int update(T t);

    int delete(long id);
}
