package com.hsany.nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOReceiveServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        while (true){
            SocketChannel accept = serverSocketChannel.accept();
            ByteBuffer buffer = ByteBuffer.allocate(512);
            int readcount =0;
            while (readcount!=-1){
                try {
                    readcount =  accept.read(buffer);
                } catch (IOException e) {
                    break;
                }
                buffer.rewind();
            };
        }
    }
}
