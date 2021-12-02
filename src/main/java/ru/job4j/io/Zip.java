package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zipFiles = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path i : sources) {
                zipFiles.putNextEntry(new ZipEntry(i.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(i.toFile()))) {
                    zipFiles.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws IOException {
            String getDir;
            String skipFile;
            String targetDir;

        if (args.length == 0) {
            throw new IllegalArgumentException("Аргументы отсутствуют. Введите аргументы.");
        }

        if (args.length == 3) {
            ArgsName argsName = ArgsName.of(args);
            getDir = argsName.get("d");
            skipFile = argsName.get("e");
            targetDir = argsName.get("o");
            Predicate<Path> condition = p -> !p.toFile().getName().endsWith(skipFile);

            List<Path> searchFile = Search.search(Paths.get(getDir), condition);

            packFiles(searchFile,
                    Paths.get(targetDir)
            );
        } else {
            throw new IllegalArgumentException("Неверное количество аргументов.");
        }


    }
}
