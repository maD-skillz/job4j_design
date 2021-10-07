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
            throw new IllegalArgumentException();
        }

        if (!args[0].contains("Xmx") && !args[0].contains("encoding")) {
            throw new IllegalArgumentException();
        }

        for (String i : args) {
            String[] getKeyAndVal = i.split("=");
            if (args[0].contains("Xmx")) {
                values.put(getKeyAndVal[0].substring(1), getKeyAndVal[1]);
            } else if (args[1].contains("Xmx")) {
                values.put(getKeyAndVal[0].substring(1), getKeyAndVal[1]);
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
