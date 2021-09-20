package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutThenGet() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(5, 99);
        assertThat(map.get(5), is(99));
    }

    @Test
    public void whenPutThenGet2() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 21);
        map.put(2, 22);
        map.put(3, 23);
        assertThat(map.get(1), is(21));
        assertThat(map.get(2), is(22));
        assertThat(map.get(3), is(23));
    }

    @Test(expected = NullPointerException.class)
    public void whenNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.get(2);
    }

    @Test(expected = NullPointerException.class)
    public void whenRemove() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.remove(1);
        map.get(1);
    }

    @Test
    public void whenExpand() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        map.put(10, "ten");
        assertThat(map.size(), is(16));
    }

    @Test
    public void whenExpandTwice() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        map.put(10, "ten");
        map.put(11, "eleven");
        map.put(12, "twelve");
        assertThat(map.size(), is(32));
    }

    @Test
    public void iteratorHasNext() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        Iterator<Integer> it = map.iterator();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorError() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        Iterator<Integer> it = map.iterator();
        it.next();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorModCountTest() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        Iterator<Integer> it = map.iterator();
        it.next();
        map.put(3, "three");
        it.next();
    }
}