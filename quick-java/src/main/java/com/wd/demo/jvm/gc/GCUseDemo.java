package com.wd.demo.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangd
 * @Date: 2023/5/14 19:46
 * -XX:+UseG1GC -Xms30m -Xmx30m -XX:+PrintGCDetails
 */
public class GCUseDemo {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        List<Object> list = new ArrayList<>();

        while (true) {
            list.add(new byte[_1MB]);
            Thread.sleep(100);
        }
    }


}
