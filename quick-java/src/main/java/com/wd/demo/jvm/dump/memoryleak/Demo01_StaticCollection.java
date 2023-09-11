package com.wd.demo.jvm.dump.memoryleak;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangd
 * @Date: 2023/5/16 15:11
 * -Xms10m -Xmx10m -XX:+PrintGCDetails
 *     // 局部变量的生命周期被放大
 *     // 代码片段 list中的object是否会变为null？为什么？
 *     // 不会。因为list中添加的是对象的引用，而不是对象本身。
 *     // 在将object赋值为null之后，list中的引用仍然指向原来的对象，而不是null。
 */
public class Demo01_StaticCollection {
    static List<Object> list = new ArrayList<>();
    public static void oomTest() {
        // Object object = new Object();
        byte[] object = new byte[2*1024*1024];
        for (int i = 0; i < 1000; i++) {
            list.add(object);
        }
        object = null;
    }

    public static void main(String[] args) throws InterruptedException {
        oomTest();
        // list中的object不会被回收
        System.gc();

        Thread.sleep(100);

        list = null;
        System.gc();
    }

}
