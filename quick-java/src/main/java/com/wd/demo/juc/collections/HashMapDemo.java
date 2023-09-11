package com.wd.demo.juc.collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: wangd
 * @Date: 2023/5/3 11:17
 */
public class HashMapDemo {

    public static void main(String[] args) {
        // thread unsafe
        Map<String, Integer> map = new HashMap<>();


        // Collections
        // Map<String, Integer> map = Collections.synchronizedMap(new HashMap<String, Integer>());

        // Map<String,Integer> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(null, null);
                map.put(UUID.randomUUID().toString().substring(0, 8), 1);
                System.out.println(Thread.currentThread().getName() + " " + map);
                // java.util.ConcurrentModificationException
            }, String.valueOf(i)).start();
        }
    }

}
