package com.wd.demo.generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangd
 * @Date: 2023/6/9 15:18
 */
public class GenericTest1 {

    @Test
    public void test1 () {
        List<? extends Father> list = null;
        // List<?> list = null;

        List<Father> list1 = new ArrayList<>();
        list1.add(new Father());
        list = list1;

        list.get(0);

        // 不允许写入数据
        list.add(null);
        // list.add(new Son());
        // list.add(new Father());
        // list.add(new Object());
        //
    }

    @Test
    public void test2 () {
        List<? super Father> list = null;

        List<Father> list1 = new ArrayList<>();
        list1.add(new Father());
        list = list1;

        list.get(0);

        // 写入数据 可以添加Father和子类对象
        list.add(null);
        list.add(new Son());
        list.add(new Father());
        // list.add(new Object());
    }
}
