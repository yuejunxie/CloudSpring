package org.mistycloud.cloud.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.*;

/**
 * @author xieyuejun
 * @created 2018/6/21 16:34
 */
public class NIOMain {

    private static Logger logger = LoggerFactory.getLogger(NIOMain.class);

    public static void main(String[] args) {

        try (RandomAccessFile randomAccessFile = new RandomAccessFile("/迅雷下载/node-v8.7.0-x64.msi", "rw")) {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(8082));
            ssc.configureBlocking(false);

            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);

//            SocketChannel socketChannel = new ServerSocketChannel();
//            socketChannel.configureBlocking(false);
//            FileChannel fileChannel = randomAccessFile.getChannel();
//            System.out.println(fileChannel.size());
//            RandomAccessFile toFile = new RandomAccessFile("copy.msi", "rw");
//            FileChannel toFileChannel = toFile.getChannel();
//            toFileChannel.transferTo(0, fileChannel.size(), fileChannel);
//            toFileChannel.size();
//            ByteBuffer byteBuffer = ByteBuffer.allocate(4);
//            int read = fileChannel.read(byteBuffer);
//            if (read != -1) {
//                byteBuffer.flip();
//            }
//            while (byteBuffer.hasRemaining()) {
//                System.out.println((char) byteBuffer.get());
//            }
//            byteBuffer.clear();
//            read = fileChannel.read(byteBuffer);
//            System.out.println(read);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
