package com.wd.demo.juc2.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: wangd
 * @Date: 2023/5/6 17:21
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(1);
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.incrementAndGet());
    }
}
