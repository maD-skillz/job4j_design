package ru.job4j.set;

public interface SetT<T> extends Iterable<T> {
    boolean add(T value);
    boolean contains(T value);
}