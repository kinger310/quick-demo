package com.wd.demo.jvm.heap;

/**
 * @Author: wangd
 * @Date: 2023/5/11 18:39
 * -Xms20m -Xmx20m
 */
public class OOMDemo {

    public static void main(String[] args) {
        byte[] bytes = new byte[30 * 1024 * 1024];
    }
}
