package com.wd.demo.design.p11_flyweight;

/**
 * @Author: wangd
 * @Date: 2023/6/1 11:18
 */
public class IntegerCacheDemo {
    private static class IntegerCache {
        static final Integer cache[];
        static {
            System.out.println("Cache 初始化");
            cache = new Integer[32];
            int j = 0;
            for(int k = 0; k < cache.length; k++)
                cache[k] = new Integer(j++);
        }
        private IntegerCache() {}
    }

    public static Integer valueOf(int i) {
        if (i >= 0 && i <= 31)
            return IntegerCache.cache[i];
        return new Integer(i);
    }


    public static void main(String[] args) {
        Integer a1 = valueOf(1);
        Integer a2 = valueOf(1);
        System.out.println(a1 == a2);
    }


}
