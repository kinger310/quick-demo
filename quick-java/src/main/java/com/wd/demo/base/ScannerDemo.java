package com.wd.demo.base;

import java.util.Scanner;

/**
 * @Author: wangd
 * @Date: 2023/8/2 11:45
 */
public class ScannerDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int i = sc.nextInt();
        int j = sc.nextInt();

        System.out.println("s = " + s);
        System.out.println("i = " + i);
        System.out.println("j = " + j);

        System.out.println("aaAA".toLowerCase());
    }
}
