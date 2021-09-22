package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                if (read % 2 == 0) {
                    System.out.println(read);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



