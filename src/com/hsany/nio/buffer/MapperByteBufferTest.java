package com.hsany.nio.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 通过映射0拷贝直接修改内存（堆外内存）
 */
public class MapperByteBufferTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();
        /**
         * 参数1 读写模式
         * 2 起始位置
         * 3 文件大小
         */
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0,(byte) 'H');
        map.put(1,(byte) 'E');
        map.put(2,(byte) 'L');
        map.put(3,(byte) 'L');
        map.put(4,(byte) 'O');
        System.out.println("修改成功");
        channel.close();

    }
}
