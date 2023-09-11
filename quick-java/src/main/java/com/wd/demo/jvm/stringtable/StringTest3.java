package com.wd.demo.jvm.stringtable;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: wangd
 * @Date: 2023/5/13 0:58
 *
 * -Xms10m -Xmx10m -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 */
public class StringTest3 {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        // short i = 0;
        for (int i = 0;;i++){
            set.add(String.valueOf(i).intern());
        }
    }

}
