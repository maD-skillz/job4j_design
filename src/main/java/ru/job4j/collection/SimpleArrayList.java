package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    private int position;

    private int expectedModCount;

    public SimpleArrayList(int capacity) {
        position = 0;
        size = 0;
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        expectedModCount = modCount;
        if (size() == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
            container[size++] = value;
        modCount++;
    }

        @Override
    public T set(int index, T newValue) {
        expectedModCount = modCount;
        Objects.checkIndex(index, size);
        container[index] = newValue;
        modCount++;
        return container[index];
    }

    @Override
    public T remove(int index) {
        expectedModCount = modCount;
        Objects.checkIndex(index, size);
        if (size - index > 1) {
            System.arraycopy(
                    container,
                    index + 1,
                    container,
                    index,
                    size - index - 1
            );
        }
        container[size--] = null;
        modCount++;
        return container[index];
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

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                return container[position++];
            }
        };
    }
}