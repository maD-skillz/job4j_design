package ru.job4j.it;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    final class ElIterator implements Iterator<T> {

       private int position;

       public ElIterator() {
          position = 0;
       }

       @Override
       public boolean hasNext() {
          return position < size;
       }

       @Override
       public T next() {
       if (!hasNext()) {
       throw new NoSuchElementException();
       }
       return (T) elements[position++];
       }
    }

    private Object[] elements;

    private int size;

    public SimpleArray(int maxSize) {
       size = 0;
       elements = new Object[maxSize];
    }

    public void add(T model) {
       if (size == elements.length) {
          throw new IllegalStateException();
       }
        elements[size++] = model;
    }

    public void set(int index, T model) {
       Objects.checkIndex(index, size);
       elements[index] = model;
    }

    public void remove(int index) {
       Objects.checkIndex(index, size);
       if (size - index > 1) {
          System.arraycopy(
                  elements, index + 1,
                  elements, index,
                  size - index - 1
          );
       }
       elements[--size] = null;
    }

    public T get(int index) {
       Objects.checkIndex(index, size);
       return (T) elements[index];
    }

    @Override
    public Iterator<T> iterator() {
       return new ElIterator();
    }
}
