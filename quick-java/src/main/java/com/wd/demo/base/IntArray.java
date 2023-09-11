package com.wd.demo.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wangd
 * @Date: 2023/6/21 11:12
 */
public class IntArray {

    public static void main(String[] args) {
        Integer[] data = {4, 5, 3, 6, 2, 5, 1};
        List<Integer> list = Arrays.asList(data);
        System.out.println("Arrays.asList(data) = " + list);
        data[0] = 999;
        System.out.println("Arrays.asList(data) = " + list);


        // int[] 转 List<Integer>
        // List<Integer> list1 = Arrays.stream(data).boxed().collect(Collectors.toList());
        // System.out.println("list1.toString() = " + list1.toString());

        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        System.out.println("list2 = " + list2.toString());
        Integer[] array = list2.toArray(new Integer[0]);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));
        list2.add(4);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));

        System.out.println(list2.get(2));
        list2.remove(2);
        System.out.println(list2);

        // Arrays.stream(arr) 可以替换成IntStream.of(arr)。
        // 1.使用Arrays.stream将int[]转换成IntStream。
        // 2.使用IntStream中的boxed()装箱。将IntStream转换成Stream<Integer>。
        // 3.使用Stream的collect()，将Stream<T>转换成List<T>，因此正是List<Integer>。
    }
}
