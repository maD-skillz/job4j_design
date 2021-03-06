package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void validateArguments(String[] args) {
        if (args.length == 2) {
            if (!Files.exists(Paths.get(args[0]))) {
                throw new IllegalArgumentException("Argument not exist!");
            }
        } else {
            throw new IllegalArgumentException("Invalid arguments number!");
        }
    }

    public static void main(String[] args) throws IOException {
        validateArguments(args);
        search(Paths.get(args[0]), p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

}