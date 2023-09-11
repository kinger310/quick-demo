package com.wd.demo.jvm.classes;

import java.util.Random;

/**
 * @Author: wangd
 * @Date: 2023/5/30 18:11
 */
public class PassiveUse2 {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(100);
        System.out.println(Serival.num);
        // 但引用其他类的话还是会初始化
        System.out.println(Serival.num2);
    }
}

interface Serival {
    public static final Thread t = new Thread() {
        {
            System.out.println("Serival初始化");
        }
    };

    public static int num = 10;
    public static final int num2 = new Random().nextInt(10);
}
