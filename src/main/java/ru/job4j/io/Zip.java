package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static final Map<String, String> values = new HashMap<>();

    public static void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Аргументы отсутствуют. Введите аргументы.");
        }

        for (String i : args) {
            String[] getKeyAndVal = i.split("=");
            if (getKeyAndVal.length == 3) {
                if (getKeyAndVal[0].startsWith("-")) {
                    values.put(getKeyAndVal[0].substring(1), getKeyAndVal[1]);
                }
                if (getKeyAndVal[1].startsWith("-")) {
                    values.put(getKeyAndVal[1].substring(1), getKeyAndVal[0]);
                }
                if (getKeyAndVal[2].startsWith("-")) {
                    values.put(getKeyAndVal[2].substring(1), getKeyAndVal[0]);
                }
                if (!getKeyAndVal[0].startsWith("-")) {
                    throw new IllegalArgumentException("Аргументы должны начинаться со знака '-'");
                }
            } else {
                throw new IllegalArgumentException("Неверное количество аргументов.");
            }
        }
    }

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zipFiles = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zipFiles.putNextEntry(new ZipEntry(sources.stream().filter(s -> s.isFile())
            .map(n -> String.valueOf(n))
            .collect(Collectors.joining("/"))));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(sources.stream()
                        .map(s -> String.valueOf(s))
                .collect(Collectors.joining("/"))))) {
                    zipFiles.write(out.readAllBytes());
                }
            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) {
        parse(args);
        packFiles(
                new File(search(Paths.get(args[0]), p -> !p.toFile().getName().endsWith(args[1])
                && p.toFile().getName().endsWith(args[0].split("/").toString()))),
                new File(args[2]));

        packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
