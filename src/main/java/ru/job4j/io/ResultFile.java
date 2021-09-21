package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        int[][] table = new int[9][9];
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[i].length; j++) {
                    table[i][j] = (i + 1) * (j + 1);
                }
            }
            try (FileOutputStream out = new FileOutputStream("result.txt")) {
                for (int[] array : table) {
                    for (int value : array) {
                        String space = value < 10 ? "  " : " ";
                        String elem = space + value;
                        out.write(elem.getBytes());
                    }
                    out.write(System.lineSeparator().getBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}


