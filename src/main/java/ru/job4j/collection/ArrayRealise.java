package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayRealise<T> implements Iterable<T> {

    final class ElIterator implements Iterator<T> {

        private int position;

        public ElIterator() {
            position = 0;
        }

        public int modCount;

        public int expectedModCount;

        @Override
        public boolean hasNext() {
           return position < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) container[position++];
        }
    }

    public Object[] container;

    private int size;

    public void add(T model) {
        if (size == container.length) {
            throw new IllegalStateException();
        }
        container[size++] = model;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    @Override
    public Iterator<T> iterator() {
       int position = 0;
        return new ElIterator();
    }
}