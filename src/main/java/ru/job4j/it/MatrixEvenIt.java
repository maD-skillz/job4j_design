package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixEvenIt implements Iterator<Integer> {
    private final int[] data;
    private int row = 0;

    public MatrixEvenIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        row = row != -1 ? getEven() : -1;
        return row != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row++];
    }

    private int getEven() {
        for (int i = row; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                return i;
            }
        }
        return -1;
    }
}
