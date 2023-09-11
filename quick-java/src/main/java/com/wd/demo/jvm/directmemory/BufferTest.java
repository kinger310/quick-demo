package com.wd.demo.jvm.directmemory;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 *  *  IO                  NIO (New IO / Non-Blocking IO)
 *  *  byte[] / char[]     Buffer
 *  *  Stream              Channel
 *  *
 *  * 查看直接内存的占用与释放
 *
 * @Author: wangd
 * @Date: 2023/5/13 16:24
 */
public class BufferTest {

    public static final int BUFFER = 1024*1024*1024;
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER);
        System.out.println("direct memory allocated");

        Scanner scanner = new Scanner(System.in);
        scanner.next();

        System.out.println("release direct memory");
        byteBuffer = null;
        System.gc();
        scanner.next();
    }
}
