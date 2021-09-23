package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        String[] searchNumber;
        String search = "404";
        String toFilter;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            StringBuilder text = new StringBuilder();
            String[] lines = text.toString().split(System.lineSeparator());
            for (String lineSplit : lines) {
                searchNumber = lineSplit.split("\\s");
                if (searchNumber[searchNumber.length - 1].equals(search)) {
                    toFilter = searchNumber[searchNumber.length - 1];
                    return in.lines().filter(e -> e.contains(toFilter)).collect(Collectors.toList());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
