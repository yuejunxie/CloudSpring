package org.mistycloud.cloud.resource.io.bnio;

import java.io.*;
import java.net.Socket;

/**
 * @author xieyuejun
 * @created 2019/2/16 14:11
 */
public class TimeClient {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        HandlerExecutorPool executorPool = new HandlerExecutorPool(8, 8);
        Runnable runnable = () -> {
            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", port);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                out.println("QUERY TIME ORDER");

                InputStream inputStream = socket.getInputStream();
                while (inputStream.available()<0){

                }
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String line = in.readLine();
                System.out.println(line);
//                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        executorPool.execute(runnable);
        executorPool.execute(runnable);
//        executorPool.execute(runnable);

    }
}
