package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
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
                            out.write("Hello\r\n\r\n".getBytes());
                            break;
                        }

                        if (str.contains("msg=Exit")) {
                            serverStatus = false;
                            break;
                        }
                        out.write("What\r\n\r\n".getBytes());
                        break;
                    }
                    out.flush();
                }
            }
        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }
}
