package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    private Node<T> head;

    public static class Node<T> {

        private T value;

        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public T delFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T del = head.value;
        head = head.next;
        return del;
    }

    public T pop() {
        return delFirst();
    }

    public void push(T value) {
        addFirst(value);
    }
}