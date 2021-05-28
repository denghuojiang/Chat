package com.hsany.nio.buffer;

import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(6);
        buffer.put(1);
        buffer.put(1);
        buffer.put(1);
        buffer.put(1);
        buffer.put(1);
        // buffer.flip(); 读写切换
        System.out.println(buffer.get());
    }
}
