package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private FileProperty nextFile;
    private HashMap<FileProperty, List<Path>> map;
    public List<Path> duplicates;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file.toAbsolutePath());
        if (map.containsKey(nextFile)) {
            duplicates = map.get(nextFile);
        } else {
            duplicates.add(file.toAbsolutePath());
            map.put(nextFile, duplicates);
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getDuplicates() {
        for (Path key : map.get(nextFile)) {
            if (map.get(nextFile).size() > 1) {
                duplicates.add(key);
            }
        }
        return duplicates;
    }
}
