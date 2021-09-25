package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        String stringToFind = "404";
        for (String i : read(file)) {
            if (stringToFind.equals(i.split(" ")[8])) {
                list.add(i);
            }
        }
        return list;
    }

    public static List<String> read(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            in.lines().forEach(list::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String i : log) {
                out.printf("%s%n", i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
        save(log, "404.txt");
    }
}
