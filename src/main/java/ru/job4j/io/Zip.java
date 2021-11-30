package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static final Map<String, String> arguments = new HashMap<>();

    public static List<Path> searcher;

    public static void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Аргументы отсутствуют. Введите аргументы.");
        }

        for (String i : args) {
            String[] getKeyAndVal = i.split("=");
            if (getKeyAndVal.length == 2) {
                if (getKeyAndVal[0].startsWith("-")) {
                    arguments.put(getKeyAndVal[0].substring(1), getKeyAndVal[1]);
                }
                if (getKeyAndVal[1].startsWith("-")) {
                    arguments.put(getKeyAndVal[1].substring(1), getKeyAndVal[0]);
                }
                if (!getKeyAndVal[0].startsWith("-")) {
                    throw new IllegalArgumentException("Аргументы должны начинаться со знака '-'");
                }
            } else {
                throw new IllegalArgumentException("Неверное количество аргументов.");
            }
        }
    }

    public static void packFiles(List<Path> sources, Path target) {
        for (Path i : sources) {
            searcher = search(i, p -> p.endsWith(arguments.get("-d"))
                    && !p.toFile().getName().endsWith(arguments.get("-e")));
        }
        try (ZipOutputStream zipFiles = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            zipFiles.putNextEntry(new ZipEntry((ZipEntry) searcher));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream((File) searcher))) {
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

    public static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(root.toFile().getAbsolutePath()))) {
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

    public static void main(String[] args) throws IOException {
        parse(args);

        packFiles(searcher,
               Paths.get(arguments.get(args[2]))
        );


        packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
