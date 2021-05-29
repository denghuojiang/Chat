package com.hsany.nio.zerocopy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NIOSendClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = null;
        long startTime = System.currentTimeMillis();
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(8080));
            System.out.println(socketChannel.finishConnect());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(socketChannel);
        FileChannel fileChannel = new FileInputStream("计算机网络（第7版）.mobi").getChannel();
        long size = fileChannel.size();
        System.out.println(size);
        int position = 0;
        long count = 0;
        count = size % (1024 * 1024 * 8) == 0 ? size / (1024 * 1024 * 8) : size / (1024 * 1024 * 8) + 1;
        for (long i = 0; i < count; i++) {
            long length = 1024 * 1024 * 8;
            if (i == count - 1) {
                length = size % (1024 * 1024 * 8);
            }
            System.out.println("第"+i+"次传输");
            fileChannel.transferTo( position, length,socketChannel);
        }
        System.out.println("花费时间"+(System.currentTimeMillis()-startTime));
        socketChannel.close();
        fileChannel.close();
    }
}
