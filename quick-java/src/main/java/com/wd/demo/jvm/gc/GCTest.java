package com.wd.demo.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangd
 * @Date: 2023/5/15 22:31
 * -Xms60m -Xmx60m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:/1.hprof
 *
 */
public class GCTest {
    public static void main(String[] args) throws InterruptedException {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            byte[] arr = new byte[100 * 1024];
            list.add(arr);
            Thread.sleep(10);
        }
    }
}
