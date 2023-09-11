package com.wd.demo.design.p06_adapter.classadapter;

/**
 * @Author: wangd
 * @Date: 2023/4/28 10:16
 */
public class Client {


    public static void main(String[] args) {
        System.out.println(" === 对象适配器模式 ====");
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());
    }
}