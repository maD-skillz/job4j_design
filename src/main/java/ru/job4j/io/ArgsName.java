package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Аргументы отсутствуют. Введите аргументы.");
        }

        for (String i : args) {
            String[] getKeyAndVal = i.split("=");
            if (getKeyAndVal.length == 2) {
                if (getKeyAndVal[0].startsWith("-")) {
                    values.put(getKeyAndVal[0].substring(1), getKeyAndVal[1]);
                }
                if (getKeyAndVal[1].startsWith("-")) {
                        values.put(getKeyAndVal[1].substring(1), getKeyAndVal[0]);
                }
                if (!getKeyAndVal[0].startsWith("-")) {
                    throw new IllegalArgumentException("Аргументы должны начинаться со знака '-'");
                }
            } else {
                throw new IllegalArgumentException("Неверное количество аргументов.");
            }
        }
    }


    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
