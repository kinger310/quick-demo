package com.wd.demo.jvm.directmemory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangd
 * @Date: 2023/5/13 16:53
 *
 * -XX:MaxDirectMemorySize=10m
 */
public class DirectMemoryTest {
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        List<ByteBuffer> buffers = new ArrayList<>();
        while (true) {
            ByteBuffer bb = ByteBuffer.allocateDirect(1024 * 1024 * 1);
            buffers.add(bb);
            Thread.sleep(1000); //为了便于观察，休眠1s
            System.out.println(i++);
        }
    }
}

