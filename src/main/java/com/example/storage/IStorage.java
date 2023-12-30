package com.example.storage;

import java.util.List;

public interface IStorage<T> {

    T create(T t);

    T read(Long id);

    List<T> readAll();

    T update(T t);

    T delete(Long id);

}
