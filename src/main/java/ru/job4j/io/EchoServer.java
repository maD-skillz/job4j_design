package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean serverStatus = true;
            while (serverStatus) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str;
                    while (in.ready()) {
                        str = in.readLine();
                        if (str.contains("msg=Hello")) {
                            System.out.println("Hello");
                            break;
                        }

                        if (str.contains("msg=Exit")) {
                            serverStatus = false;
                            break;
                        }
                        System.out.println("What");
                        break;
                    }
                    out.flush();
                }
            }
        }
    }
}
