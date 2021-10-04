package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnalysisTest {
    private final String source = "server_log.csv";
    private final String target = "server_offline.csv";

    @Test
    public void serverCheck1() {
        Analysis analysis = new Analysis();
        writeToFile(source, List.of(
                "200 10:56:01",
        "500 10:57:01",
        "400 10:58:01",
        "200 10:59:01",
        "500 11:01:02",
        "200 11:02:02"
        ));
        analysis.unavailable(source, target);
        assertThat(getFileInfo(target),
                is("[10:57:01;10:59:01, "
                        + "11:01:02;11:02:02]"));
    }

    @Test
    public void serverCheck2() {
        Analysis analysis = new Analysis();
        writeToFile(source, List.of(
                "200 10:56:01",
        "500 10:57:01",
        "400 10:58:01",
        "500 10:59:01",
        "400 11:01:02",
        "200 11:02:02"
        ));
        analysis.unavailable(source, target);
        assertThat(getFileInfo(target), is("[10:57:01;11:02:02]"));
    }

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void temporaryFolderTest() throws IOException {
        Analysis analysis = new Analysis();
        File src = tempFolder.newFile(source);
        File tgt = tempFolder.newFile(target);
        writeToFile(src.getAbsolutePath(), List.of(
                    "200 10:56:01",
                    "500 10:57:01",
                    "400 10:58:01",
                    "500 10:59:01",
                    "400 11:01:02",
                    "200 11:02:02"
            ));
            analysis.unavailable(src.getAbsolutePath(), tgt.getAbsolutePath());
            assertThat(getFileInfo(tgt.getAbsolutePath()), is("[10:57:01;11:02:02]"));
        }


    private String getFileInfo(String path) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(path)
        )) {
            list = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list.toString();
    }

    private void writeToFile(String file, List<String> list) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(file))) {
            list.forEach(e -> out.write(e + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}