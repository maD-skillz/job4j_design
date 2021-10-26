package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private FileProperty nextFile;
    private HashMap<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        long fileSize = Files.size(file);
        String fileName = file.getFileName().toString();
        nextFile = new FileProperty(fileSize, fileName);
        if (map.containsKey(nextFile)) {
            map.get(nextFile).add(file);
        } else {
            List<Path> duplicates = new ArrayList<>();
            duplicates.add(file.toAbsolutePath());
            map.put(nextFile, duplicates);
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getDuplicates() {
        List<Path> duplicates = new ArrayList<>();
        for (Path key : map.get(nextFile)) {
            if (map.get(nextFile).size() > 1) {
                duplicates.add(key);
            }
        }
        return duplicates;
    }
}
