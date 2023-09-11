package com.wd.demo.jvm.heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: wangd
 * @Date: 2023/5/12 1:17
 * -XX:+PrintGCDetails -Xms600m -Xmx600m
 */
public class HeapInstanceDemo {

    byte[] buffer = new byte[new Random().nextInt(1024 * 200)];

    public static void main(String[] args) {
        ArrayList<HeapInstanceDemo> list = new ArrayList<HeapInstanceDemo>();
        while (true) {
            list.add(new HeapInstanceDemo());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
