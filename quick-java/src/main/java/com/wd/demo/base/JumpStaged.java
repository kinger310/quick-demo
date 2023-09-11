package com.wd.demo.base;

import java.util.Arrays;

/**
 * @Author: wangd
 * @Date: 2023/7/4 16:35
 */
public class JumpStaged {
    public static void main(String[] args) {
        JumpStaged j = new JumpStaged();
        System.out.println(j.stages(3));
    }

    public int stages(int n) {
        int[] dp = new int[n+3];
        dp[0] = dp[1] = dp[2] = 1;
        for (int i = 3; i < n+1; i++) {
            dp[i] = dp[i-1]+dp[i-3];
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}
