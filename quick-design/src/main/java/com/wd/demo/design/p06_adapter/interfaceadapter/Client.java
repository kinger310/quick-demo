package com.wd.demo.design.p06_adapter.interfaceadapter;

import com.wd.demo.design.p06_adapter.objectadapter.Phone;
import com.wd.demo.design.p06_adapter.objectadapter.Voltage220V;
import com.wd.demo.design.p06_adapter.objectadapter.VoltageAdapter;

/**
 * @Author: wangd
 * @Date: 2023/4/28 10:16
 */
public class Client {

    public static void main(String[] args) {
        AbsAdapter absAdapter = new AbsAdapter() {
            @Override
            public void m1() {
                super.m1();
                System.out.println("不想全部实现所有接口方法，使用接口适配器");
            }
        };
        absAdapter.m1();
    }
}