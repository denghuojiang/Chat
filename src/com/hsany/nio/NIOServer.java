package com.hsany.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            if (selector.select(2000) == 0) {
                System.out.println("服务器等了两秒无任何事发生");
                continue;
            }

            // 返回关注事件的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                if (key.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    if (socketChannel == null) {
                        System.out.println("lianjieweikong");
                        continue;
                    }
                    System.out.println(socketChannel);
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                }
                else if (key.isReadable()) {
//                    System.out.println("可读事件");
                    SocketChannel socket = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    int read = socket.read(buffer);
                    while (read > 0) {
                        read = socket.read(buffer);
                        buffer.flip();
                        System.out.println(new String(buffer.array()));
                        buffer.clear();
                    }
                    if(read ==-1){
                        socket.close();
                    }

                    System.out.println("读取结束");
                    // 手动删除当前selectKey


                }
                iterator.remove();
            }
        }
    }
}
