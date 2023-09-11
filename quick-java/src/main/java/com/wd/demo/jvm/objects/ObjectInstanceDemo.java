package com.wd.demo.jvm.objects;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author: wangd
 * @Date: 2023/5/12 20:29
 */
public class ObjectInstanceDemo {

    int[] arr;

    public ObjectInstanceDemo(int[] arr) {
        this.arr = arr;
    }

    static class Father {
        int i;

    }
     static class Son extends Father {
        int j;


    }

    public static void main(String[] args) {
        // ObjectInstanceDemo o  = new ObjectInstanceDemo(new int[]{1,2,3,4,5,6,7,8,9,0});

        Son o = new Son();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
