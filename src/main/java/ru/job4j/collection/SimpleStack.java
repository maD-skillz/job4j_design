package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    private Node<T> head;

    private int size;

    public static class Node<T> {

        private T value;

        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public void addFirst(T value) {
        Node<T> firstNode = new Node<>(value, null);
        if (head == null) {
            head = firstNode;
        } else {
            firstNode.next = head;
            head = firstNode;
        }
        size++;
    }

    public void addLast(T value) {
        Node<T> lastNode = new Node<>(value, null);
        Node<T> temp = lastNode.next;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = lastNode;
        size++;
    }

    public T delFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T del = head.value;
        head = head.next;
        size--;
        return del;
    }

    public T pop() {
        return delFirst();
    }

    public void push(T value) {
        addFirst(value);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}