package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    private int position;

    private int expectedModCount;

    public SimpleArrayList(int capacity) {
        size = 0;
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size() == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
            container[size++] = value;
        modCount++;
    }

        @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        modCount++;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        System.arraycopy(
                    container,
                    index + 1,
                    container,
                    index,
                    container.length - index - 1
            );
        container[container.length - 1] = null;
        size--;
        modCount++;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        expectedModCount = modCount;
        position = 0;

        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return position < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[position++];
            }
        };
    }
}