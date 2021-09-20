package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private int expectedModCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int i = indexOf(key);
        if (table[i] != null) {
            return false;
        }
        table[i] = new MapEntry<>(key, value);
        count++;
        modCount++;
        expand();
        return true;
    }

    private int hash(K key) {
        int h = key.hashCode();
        return key == null ? 0 : (h ^ (h >>> 16));
    }

    private int indexOf(K key) {
        return hash(key) & capacity - 1;
    }

    private void expand() {
        if (count == (capacity * LOAD_FACTOR)) {
        capacity *= 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> index : oldTable) {
            if (index != null) {
                table[index.key.hashCode() & (capacity - 1)] = index;
                }
            }
        }
    }

    public int size() {
        return capacity;
    }

    @Override
    public V get(K key) {
        int keyHash = indexOf(key);
        int indexKey = table[keyHash].key.hashCode();
        if (indexKey != keyHash) {
                return null;
            }
        return table[indexOf(key)].value;
    }

    @Override
    public boolean remove(K key) {
        if (key == null) {
            return false;
        }
        int keyHash = indexOf(key);
        int indexKey = table[keyHash].key.hashCode();
        if (indexKey != keyHash) {
            return false;
        }
        table[indexOf(key)] = null;
        modCount++;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        expectedModCount = modCount;
        return new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (index == capacity) {
                    return false;
                }
                if (table[index] == null) {
                    index++;
                    return hasNext();
                }
                return true;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}