package com.wd.demo.interview.ximalaya;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: wangd
 * @Date: 2023/7/12 14:49
 * 有序升序的数组，删除重复元素，返回一个新数数组，顺序保持原来的相对顺序。
 * [1,1,2,2,3,3]  [1,2,3]
 */
public class AAA {

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2,3,3};
        System.out.println(Arrays.toString(new AAA().dedup(arr)));

    }

    private Integer[] dedup(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // int[] newArr = new int[arr.length];
        List<Integer> newArr = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
                newArr.add(arr[i]);
            }
        }
        // System.out.println(newArr);
        return newArr.toArray(new Integer[0]);

    }
}
