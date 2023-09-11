package com.wd.demo.jvm.gc;

/**
 * @Author: wangd
 * @Date: 2023/5/13 20:38
 */
public class RefCountGC {

    private byte[] bigSize = new byte[5*1024*1024];

    Object ref = null;

    public static void main(String[] args) {
        RefCountGC obj1 = new RefCountGC();
        RefCountGC obj2 = new RefCountGC();

        obj1.ref = obj2;
        obj2.ref = obj1;

        obj1 = null;
        obj2 = null;
        // 显示的执行垃圾收集行为
        // 这里发生GC，obj1和obj2是否被回收？
        System.gc();

    }
}
