package com.wd.demo.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: wangd
 * @Date: 2023/7/27 16:13
 */
public class ForLoopDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(2);
        list.add(3);

        // wrong usage
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                list.remove(i);
            }
        }
        System.out.println("list = " + list);


        // better usage
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 0) {
                iterator.remove();
            }
        }
        System.out.println("list = " + list);
    }
}
