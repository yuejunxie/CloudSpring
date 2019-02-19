package org.mistycloud.cloud.resource.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xieyuejun
 * @created 2019/2/16 13:54
 */
@Slf4j
public class TimeServer {
    private static final int port = 8080;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new TimeTask(socket)).start();
            }
        }
    }

    static class TimeTask implements Runnable {
        private Socket socket;

        public TimeTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                String body;
                while (true) {
                    body = in.readLine();
                    if (body != null) {
                        break;
                    }
                }
                System.out.println("The time server receive order : " + body);
                String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                        new java.util.Date(System.currentTimeMillis()).toString()
                        : "BAD ORDER";
                out.println(currentTime);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }

        }
    }
}
