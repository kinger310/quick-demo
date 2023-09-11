package com.wd.demo.jvm.stack;

/**
 * @Author: wangd
 * @Date: 2023/5/10 19:20
 */
public class OperandDemo {

    public void foo(){
        long a = 3;
        int b = 5;
        long c = a + b;
    }

    public void bar(){
        byte a = 15;
        int b = 8;
        int c = a + b;
    }

    public int fooRet() {
        int m = 10;
        int n = 20;
        return m + n;
    }

    public int getSum() {
        int i = fooRet();
        return 0;
    }

    public static void main(String[] args) {

    }
}
