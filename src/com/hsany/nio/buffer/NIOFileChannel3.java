package com.hsany.nio.buffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel3 {
    public static void main(String[] args) throws IOException {

        FileChannel in = new FileInputStream("1.txt").getChannel();
        FileChannel out = new FileOutputStream("2.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(5);
        while (in.read(buffer)!=-1){
            buffer.flip();
            out.write(buffer);

            // 要用clear 不能用limit 否则会使每次buffer的容量变小limit = position;
            buffer.clear();

        }
        in.close();
        out.close();
    }
}
