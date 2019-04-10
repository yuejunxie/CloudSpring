package org.mistycloud.cloud.resource.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author xieyuejun
 * @created 2019/2/16 14:11
 */
public class TimeClient {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        Socket socket = new Socket("127.0.0.1", port);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println("QUERY TIME ORDER");
            String line = in.readLine();
            System.out.println(line);
        }
    }
}
