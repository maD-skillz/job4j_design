package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean serverStatus = true;
            String str;
            while (serverStatus) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    str = in.readLine();
                    while (in.ready()) {
                        if (str.contains("msg=Bye")) {
                            serverStatus = false;
                            break;
                        }
                        System.out.println(str);
                        break;
                    }
                    out.flush();
                }
            }
        }
    }
}
