package com.wd.demo.spring6.aop;

import org.springframework.stereotype.Component;

/**
 * @Author: wangd
 * @Date: 2023/6/2 21:01
 */
@Component
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int x, int y) {
        int result = x + y;
        System.out.println("x+y = " + result);
        return result;
    }

    @Override
    public int minus(int x, int y) {
        int result = x - y;
        System.out.println("x-y = " + result);
        return result;
    }

    @Override
    public int mul(int x, int y) {
        int result = x * y;
        System.out.println("x*y = " + result);
        return result;
    }

    @Override
    public int div(int x, int y) {
        int result = x / y;
        System.out.println("x/y = " + result);
        return result;
    }

}
