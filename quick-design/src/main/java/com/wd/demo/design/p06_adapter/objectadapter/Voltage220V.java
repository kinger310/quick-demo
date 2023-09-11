package com.wd.demo.design.p06_adapter.objectadapter;

/**
 * @Author: wangd
 * @Date: 2023/4/28 10:18
 */
public class Voltage220V {

    public int output220V() {
        int src = 220;
        System.out.println("电压=" + src + "伏");
        return src;
    }
}
