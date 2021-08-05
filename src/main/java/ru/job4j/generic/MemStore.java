package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class MemStore<T extends ru.job4j.generic.Base> implements ru.job4j.generic.Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (findById(id) == null) {
            return false;
        }
        mem.put(id, model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (findById(id) == null) {
            return false;
        }
        mem.remove(id);
        return true;
    }

    @Override
    public T findById(String id) {
        return mem.get(id);
    }
}