package com.wd.demo.jvm.stack;

/**
 * @Author: wangd
 * @Date: 2023/5/10 20:27
 */
public class IPlusPlusDemo {

    public void add1() {
        int i = 10;
        i++;
        int j = 10;
        ++j;
    }

    public void add2() {
        int i = 10;
        int k = i++;
        int j = 10;
        int m = ++j;
    }

    public void add3() {
        int i = 10;
        i = i++;
        int j = 10;
        j = ++j;
        System.out.println("i =  " + i +" j = " + j);
    }

    public void add4() {
        int i = 10;
        int j = i++ + i++ + ++i;
        System.out.println("i = " + i +" j = " + j);
    }

    public final void add5() {
        int i = 10;
        int j = ++i + ++i + i++;
        System.out.println("i = " + i +" j = " + j);
    }

    public static void main(String[] args) {
        new IPlusPlusDemo().add4();
        new IPlusPlusDemo().add5();
    }
}
