package com.wd.demo.juc2.vlt;

/**
 * @Author: wangd
 * @Date: 2023/5/6 9:27
 */
public class JSLDemo {



    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                MyResourceDemo.one();
            }
        });
        thread.start();
        new Thread(() ->{
            // thread.isAlive()
            for (int i = 0; i < 10; i++) {

                MyResourceDemo.two();
            }
        }).start();

    }
}

class MyResourceDemo {
    static volatile int i = 0, j = 0;
    static synchronized void one() { i++; j++; }
    static void two() {
        System.out.println("i=" + i + " j=" + j);
    }
}