package com.wd.demo.interview.ximalaya;

import java.util.Arrays;

/**
 * @Author: wangd
 * @Date: 2023/7/12 18:17
 */
public class AAA2 {

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2,2,3,3,4,4,4};
        System.out.println(Arrays.toString(new AAA2().removeDup(arr)));
    }

    private int[] removeDup(int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        while (j < n) {
            if(nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
            j++;
        }
        return Arrays.copyOfRange(nums, 0, i+1);
    }


}
