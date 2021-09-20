package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements SetT<T> {

    private final SimpleArrayList<T> set = new SimpleArrayList(16);

    @Override
    public boolean add(T value) {
       if (contains(value)) {
           return false;
       }
        set.add(value);
       return true;
    }

    @Override
    public boolean contains(T value) {
        for (T i : set) {
            if (Objects.equals(i, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}