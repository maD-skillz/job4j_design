package ru.job4j.generic;

public interface Store<T extends ru.job4j.generic.Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}