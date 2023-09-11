package com.wd.demo.jvm.gc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: wangd
 * @Date: 2023/5/14 0:11
 */
public class GCRootsTest {
    public static void main(String[] args) throws InterruptedException {
        List<Object> numList = new ArrayList<>();
        Date date = new Date();

        for (int i = 0; i < 100; i++) {
            numList.add(String.valueOf(i));
            Thread.sleep(10);
        }
        System.out.println("数据添加完毕，请操作：");
        new Scanner(System.in).next();
        numList = null;
        date = null;

        System.out.println("numList、birth已置空，请操作：");
        new Scanner(System.in).next();

        System.out.println("结束");    }
}
