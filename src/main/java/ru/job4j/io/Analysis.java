package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Analysis {

    public void unavailable(String source, String target) {
        List<String> result = new ArrayList<>();
        StringJoiner temp = new StringJoiner(";");
        Iterator<String> it = reader(source).iterator();
        reader(source).forEach(e -> {
            while (it.hasNext()) {
                String[] data = it.next().split(" ");
                if (data[0].equals("400") || data[0].equals("500")) {
                    temp.add(data[1]);
                    result.add(temp.toString());
                    break;
                } else if (data[0].equals("200") || data[0].equals("300")) {
                    temp.add(data[1]);
                    result.add(temp.toString());
                    break;
                }
            }
        });
        writer(target, result);
    }

    private List<String> reader(String source) {
        List<String> list = new ArrayList<>();
        try (BufferedReader out = new BufferedReader(
                new FileReader(source))) {
          list = out.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void writer(String target, List<String> list) {
        try (PrintWriter in = new PrintWriter(
                new FileOutputStream(target))) {
            list.stream().forEach(e -> in.write(e + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("server_log.csv", "server_offline.csv");
    }
}