package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
      try (BufferedReader in = new BufferedReader(new FileReader(source));
      BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
          StringBuilder builder = new StringBuilder();
          in.lines().forEach(e -> {
              String[] data = e.split(" ");
              if (data[0].equals("400") || data[0].equals("500")) {
                 if (builder.length() == 0) {
                     builder.append(data[1]);
                 }
              } else if (data[0].equals("200") || data[0].equals("300")) {
                  if (builder.length() != 0) {
                      builder.append(";").append(data[1]);
                      try {
                          out.write(builder.toString() + System.lineSeparator());
                      } catch (IOException ioException) {
                          ioException.printStackTrace();
                      }
                      builder.setLength(0);
                  }
              }
          });
          if (builder.length() != 0) {
              out.write(builder.toString());
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
    }
}
