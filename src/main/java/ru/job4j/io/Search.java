package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static Path start;
    public static String ext;

    public static void validateArguments(String[] args) {
       start = Paths.get(args[0]);
        ext = args[1];

        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Invalid argument number.");
        }

        if (!Files.exists(start)) {
            throw new IllegalArgumentException("Path is not exist!");
        }

        if (ext == null) {
            throw new IllegalArgumentException("Extension absent.");
        }
    }

    public static void main(String[] args) throws IOException {
        start = Paths.get(args[0]);
        ext = args[1];
        search(start, p -> p.toFile().getName().endsWith(ext)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

}