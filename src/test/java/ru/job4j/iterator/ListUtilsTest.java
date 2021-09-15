package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenRemoveIfEven() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListUtils.removeIf(input, (v) -> v % 2 == 1);
        assertThat(Arrays.asList(2, 4, 6), Is.is(input));
    }

    @Test
    public void whenReplaceIfGreater10() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15, 17));
        ListUtils.replaceIf(input, (v) -> v > 8, 20);
        assertThat(Arrays.asList(1, 3, 5, 7, 20, 20, 20, 20, 20), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> removed = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        ListUtils.removeAll(input, removed);
        assertThat(Arrays.asList(2, 4, 6, 8), Is.is(input));
    }
}