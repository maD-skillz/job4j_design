package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> firstNode;

    private Node<E> lastNode;

    private int size = 0;

    private int modCount;

    private int expectedModCount;

    private class Node<E> {
        private E currEl;
        private Node<E> nextEl;
        private Node<E> prevEl;

        private Node(E currEl, Node<E> prevEl, Node<E> nextEl) {
            this.currEl = currEl;
            this.prevEl = prevEl;
            this.nextEl = nextEl;
        }

        public E getCurrEl() {
            return currEl;
        }

        public void setCurrEl(E currEl) {
            this.currEl = currEl;
        }

        public Node<E> getNextEl() {
            return nextEl;
        }

        public void setNextEl(Node<E> nextEl) {
            this.nextEl = nextEl;
        }

        public Node<E> getPrevEl() {
            return prevEl;
        }

        public void setPrevEl(Node<E> prevEl) {
            this.prevEl = prevEl;
        }
    }

    public SimpleLinkedList() {
        lastNode = new Node<E>(null, firstNode, null);
        firstNode = new Node<E>(null, null, lastNode);
    }

    @Override
    public void add(E value) {
        Node<E> prev = lastNode;
        prev.setCurrEl(value);
        lastNode = new Node<>(null, prev, null);
        prev.setNextEl(lastNode);
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> getEl = firstNode.getNextEl();
        for (int i = 0; i < index; i++) {
            getEl = getNextEl(getEl);
        }
        return getEl.getCurrEl();
    }

    private Node<E> getNextEl(Node<E> current) {
        return current.getNextEl();
    }

    public Node<E> curr;

    @Override
    public Iterator<E> iterator() {
        curr = firstNode;
        expectedModCount = modCount;
        return new Iterator<E>() {
            int position = 0;


            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return firstNode.getNextEl() == lastNode.getPrevEl();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new IllegalStateException();
                }

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                E res = curr.getCurrEl();
                curr = curr.getNextEl();

                return res;
            }
        };
    }
}
