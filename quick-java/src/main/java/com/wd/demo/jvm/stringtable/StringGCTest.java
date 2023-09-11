package com.wd.demo.jvm.stringtable;

/**
 *
 * @Author: wangd
 * @Date: 2023/5/13 17:04
 *
 *  String的垃圾回收:
 *  -Xms15m -Xmx15m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails
 */
public class StringGCTest {

    public static void main(String[] args) {
        // String s = "Java";
        for (int i = 0; i < 100000; i++) {
            String.valueOf(i).intern();
        }
    }
}
