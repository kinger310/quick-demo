package com.wd.demo.juc.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Author: wangd
 * @Date: 2023/5/4 10:06
 */
public class CookNoodles {
    
    public static void main(String[] args) {
        // Noodle, Water, Vegetables
        long start = System.currentTimeMillis();



        FutureTask<String> hotWater = new FutureTask<>(new HotWater());
        new Thread(hotWater, "Water").start();
        FutureTask<String> cleanVegetables = new FutureTask<>(() -> {
            Thread.sleep(2000);
            System.out.println("洗菜花了2分钟");
            return "cleanVegetables";
        });
        new Thread(cleanVegetables, "Vegetables").start();

        try {
            String water = hotWater.get();
            String veg = cleanVegetables.get();
            cookNoodles(water, veg, "noodles");
            System.out.println("总共花费时间 : " + (System.currentTimeMillis() - start));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }


    }

    private static void cookNoodles(String water, String veg, String noodles) {
        System.out.println("a delicious noodles done!");
    }


    static class HotWater implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(5000);
            System.out.println("烧水花了5分钟");
            return "hotWater";
        }
    }
}
