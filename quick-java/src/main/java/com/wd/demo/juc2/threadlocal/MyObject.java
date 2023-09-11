package com.wd.demo.juc2.threadlocal;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @Author: wangd
 * @Date: 2023/5/8 2:36
 */
public class MyObject {
    boolean flag = false;

    @Override
    public int hashCode() {
        return -super.hashCode();
    }

    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(VM.current().details());

        MyObject obj = new MyObject();
        // String obj = new String("hello");
        // System.out.println(obj + " 十六进制哈希：" + Integer.toHexString(obj.hashCode()));
        // hashcode只有被调用才会分配值
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
