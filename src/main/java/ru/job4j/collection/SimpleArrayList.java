package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    private int expectedModCount;

    private int position;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            throw new IllegalStateException();
        }
        if (size() == container.length - 1) {
            container = Arrays.copyOf(container, container.length * 2);
        }
            container[position++] = value;
        expectedModCount = modCount;
        modCount++;

        }

        @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        container[index] = newValue;
        expectedModCount = modCount;
        modCount++;
        return newValue;
    }

    @Override
    public T remove(int index) {
        size = modCount;
        Objects.checkIndex(index, container.length);
        if (size - index >= 1) {
            System.arraycopy(
                    container,
                    index + 1,
                    container,
                    index,
                    size - 1
            );
        }
        expectedModCount = modCount;
        modCount++;
        return container[size];
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        return modCount;
    }

    @Override
    public Iterator<T> iterator() {
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[position++];
            }
        };
    }
}