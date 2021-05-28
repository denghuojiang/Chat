package com.hsany.nio.buffer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class NIOFileChannel {
    public static void main(String[] args) {
        String str = "hello world";
        FileChannel channel = null;

        try {
            FileOutputStream file = new FileOutputStream("E:\\file01.txt");
            channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(str.getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
