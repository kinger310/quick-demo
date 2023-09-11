package com.wd.demo.juc.collections;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: wangd
 * @Date: 2023/5/3 11:17
 */
public class HashSetDemo {

    public static void main(String[] args) {
        // thread unsafe
        // Set<String> set = new HashSet<>();

        // Collections
        // Set<String> set = Collections.synchronizedSet(new HashSet<>());

        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
                // logger.info(list.toString());
                // java.util.ConcurrentModificationException
            }, String.valueOf(i)).start();
        }
    }

}
