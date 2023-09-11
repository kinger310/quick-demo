package com.wd.demo.base;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author: wangd
 * @Date: 2023/6/9 15:52
 */
public class LinkedTest {

    @Test
    public void testClear() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.clear();
    }

    @Test
    public void testArrLink() {
        ArrayList<Integer> arr = new ArrayList<>(1000000);
        arr.add(0, 1);

        LinkedList<Integer> l = new LinkedList<>();
        arr.add(0, 1);
     }
}
