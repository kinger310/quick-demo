package com.wd.demo.jvm.heap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: wangd
 * @Date: 2023/6/8 17:21
 * 作者：芋道源码
 * 链接：https://zhuanlan.zhihu.com/p/151028855
 */
public class MultiThreadOOM {
        public static void main(String[] args) {
            new Thread(() -> {
                List<byte[]> list = new ArrayList<byte[]>();
                while (true) {
                    System.out.println(new Date().toString() + Thread.currentThread() + "==");
                    byte[] b = new byte[1024 * 1024 * 1];
                    list.add(b);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            // 线程二
            new Thread(() -> {
                List<byte[]> list = new ArrayList<byte[]>();
                while (true) {
                    System.out.println(new Date().toString() + Thread.currentThread() + "==");
                    byte[] b = new byte[1024 * 100];
                    list.add(b);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

}
