package org.comicbookret.controller;

import java.util.List;

public interface CommonController<T> {
    List<T> findAll();
    String create(T t);
    T read(T t);
    String update(T t);
    String delete(T t);
}
