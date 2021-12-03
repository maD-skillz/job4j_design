package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
       try (BufferedReader in = new BufferedReader(new FileReader(path))) {
             in.lines().forEach(e -> {
                 if (!e.isEmpty() && !e.startsWith("#")) {
                 String[] el = e.split("=");
                 String key = el[0].trim();
                 if (el.length != 2 || key.isEmpty()) {
                     throw new IllegalArgumentException();
                 }
                 values.put(el[0], el[1]);
                }
             });
        } catch (IOException e) {
             e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}