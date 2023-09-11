package com.wd.demo.jvm.methodarea;

/**
 * @Author: wangd
 * @Date: 2023/5/12 16:47
 */
public class StaticFieldStoreDemo {


    static long start;
    static {
        start = System.currentTimeMillis();
        System.out.println(start);
    }
    static byte[] bytes = new byte[100 * 1024 *1024];

    public static void main(String[] args) throws InterruptedException {
        System.out.println((System.currentTimeMillis() - start));
        System.out.println("start");
        // Thread.sleep(1000000);
        System.out.println("end");
    }

}
