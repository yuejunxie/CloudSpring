package org.mistycloud.cloud.resource.io.bnio;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xieyuejun
 * @created 2019/2/16 13:54
 */
@Slf4j
public class TimeServer {
    private static final int port = 8080;

    public static void main(String[] args) throws IOException {
        HandlerExecutorPool executorPool = new HandlerExecutorPool(1, 1);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println(dateFormat.format(new Date()));
                try {
                    executorPool.execute(new TimeTask(socket));
                }catch (Exception e){
                    e.printStackTrace();
                    socket.close();
                }
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
                Thread.sleep(5000);
                socket.close();
                System.out.println("end");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
