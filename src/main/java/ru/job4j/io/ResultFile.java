package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        int[][] table = new int[9][9];
                try (FileOutputStream out = new FileOutputStream("result.txt")) {
                    for (int i = 0; i < table.length; i++) {
                        for (int j = 0; j < table[i].length; j++) {
                            String stringTable = (i + 1) * (j + 1) + " ";
                            out.write(stringTable.getBytes());
                        }
                        out.write(System.lineSeparator().getBytes());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


