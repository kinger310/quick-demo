package com.wd.demo.interview.ximalaya;

import java.util.Scanner;

/**
 * @Author: wangd
 * @Date: 2023/7/14 10:57
 * 【程序12】题目：企业发放的奖金根据利润提成。
 * 利润(I)低于或等于10万元时，奖金可提10%；
 * 利润高于10万元，低于20万元时，低于10万元的部分按10%提成，高于10万元的部分，可提成7.5%；
 * 20万到40万之间时，高于20万元的部分，可提成5%；
 * 40万到60万之间时高于40万元的部分，可提成3%；
 * 60万到100万之间时，高于60万元的部分，可提成1.5%，
 * 高于100万元时，超过100万元的部分按1%提成，从键盘输入当月利润revenue，求应发放奖金总数sum？
 */
public class Award {
    public static final int QUICK_1 = 10000;
    public static final int QUICK_2 = 17500;
    public static final int QUICK_3 = 27500;
    public static final int QUICK_4 = 33500;
    public static final int QUICK_5 = 39500;

    public static void main(String[] args) {
        // double a = 100000*0.075+200000*0.05+200000*0.03+400000*0.015;
        // System.out.println(a);

        char[][] a = new char[][]{{'1','1'}, {'1','1'}};
        if (a[0][0] == '1'){
            System.out.println("true");
        }
        System.out.println("输入当月利润revenue");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        double revenue = Double.parseDouble(next);
        System.out.println(award(revenue));

    }

    public static double award(double revenue) {
        double ans = 0.0D;
        if (revenue <= 100000) {
            ans = revenue * 0.1;
        } else if (revenue < 200000) {
            ans = (revenue - 100000) * 0.075 + QUICK_1;
        } else if (revenue < 400000) {
            ans = (revenue - 200000) * 0.05 + QUICK_2;
        } else if (revenue < 600000) {
            ans = (revenue - 400000) * 0.03 + QUICK_3;
        } else if (revenue < 1000000) {
            ans = (revenue - 600000) * 0.015 + QUICK_4;
        } else {
            ans = (revenue- 1000000) * 0.01 + QUICK_5;
        }
        return ans;
    }
}
