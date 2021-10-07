package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayRealiseTest {

/*    @Test
    public void whenAddThenGet() {
        ArrayRealise<String> array = new ArrayRealise<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        ArrayRealise<String> array = new ArrayRealise<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        ArrayRealise<String> array = new ArrayRealise<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        ArrayRealise<String> array = new ArrayRealise<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        ArrayRealise<String> array = new ArrayRealise<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        ArrayRealise<String> array = new ArrayRealise<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    } */
}