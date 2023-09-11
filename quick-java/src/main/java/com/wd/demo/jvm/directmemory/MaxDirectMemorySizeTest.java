package com.wd.demo.jvm.directmemory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 *  * -Xmx20m -XX:MaxDirectMemorySize=10m
 * @Author: wangd
 * @Date: 2023/5/13 16:34
 */
public class MaxDirectMemorySizeTest {
    public static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }

}
