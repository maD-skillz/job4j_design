package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage java -jar search.jar ROOT_FOLDER.");
        }

        if (!args[0].startsWith(".")) {
            throw new IllegalArgumentException("Path must stars with '.'.");
        }

        if (!args[1].endsWith("txt")) {
            throw new IllegalArgumentException("Path end must be 'js'.");
        }
        Path start = Paths.get(".");
        search(start, p -> p.toFile().getName().endsWith("txt")).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

}