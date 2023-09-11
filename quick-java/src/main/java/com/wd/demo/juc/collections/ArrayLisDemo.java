package com.wd.demo.juc.collections;

import java.util.*;

/**
 * @Author: wangd
 * @Date: 2023/5/3 11:17
 */
public class ArrayLisDemo {

    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(1, 2);
        System.out.println();
        String s = "aaabbb";
        System.out.println(Arrays.toString(s.split("a")));

        // thread unsafe
        List<String> list = new ArrayList<>();
        // Collections
        // List<String> list = Collections.synchronizedList(new ArrayList<>());

        // List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
                // logger.info(list.toString());
                // java.util.ConcurrentModificationException
            }, String.valueOf(i)).start();
        }
    }

}
