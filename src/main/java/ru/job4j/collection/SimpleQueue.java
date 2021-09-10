package ru.job4j.collection;


public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public void addAll() {
        while (in.size() > 0) {
            T trans = in.pop();
            out.push(trans);
        }
    }

    public T poll() {
        if (out.size() == 0) {
            addAll();
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}