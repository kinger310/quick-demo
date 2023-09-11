package com.wd.demo.jvm.heap;

/**
 * @Author: wangd
 * @Date: 2023/5/11 18:39
 * -Xms60m -Xmx60m -XX:+PrintGCDetails
 */
public class BigObj2OldGenDemo {

    public static void main(String[] args) {
        byte[] bytes = new byte[30 * 1024 * 1024];
    }
}
