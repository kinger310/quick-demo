package com.wd.demo.jvm.heap;

/**
 * @Author: wangd
 * @Date: 2023/5/11 16:37
 * -Xms30m -Xmx30m
 */
public class HeapDemo {
    public static void main(String[] args) {
        System.out.println("start...");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end...");
    }
}
