package com.wd.demo.jvm.jit;

/**
 * @Author: wangd
 * @Date: 2023/5/14 1:27
 *  * 测试解释器模式和JIT编译模式
 *  *  -Xint  : 4520ms
 *  *  -Xcomp : 265ms
 *  *  -Xmixed : 254ms
 */
public class JITDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        testPrimeNumber(1000000);

        long end = System.currentTimeMillis();

        System.out.println("花费的时间为：" + (end - start));

    }

    public static void testPrimeNumber(int count){
        for (int i = 0; i < count; i++) {
            //计算100以内的质数
            label:for(int j = 2;j <= 100;j++){
                for(int k = 2;k <= Math.sqrt(j);k++){
                    if(j % k == 0){
                        continue label;
                    }
                }
                //System.out.println(j);
            }

        }
    }
}
