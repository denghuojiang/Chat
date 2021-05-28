package com.hsany.nio.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class NIOFileChannel4 {
    public static void main(String[] args) throws IOException {
        FileChannel in = new FileInputStream("EOuU162UEAESUZU.jpg").getChannel();
        FileChannel out = new FileOutputStream("flyBird.jpg").getChannel();
        out.transferFrom(in,0,in.size());

        in.close();
        out.close();
    }
}
