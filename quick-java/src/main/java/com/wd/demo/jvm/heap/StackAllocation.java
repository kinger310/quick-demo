package com.wd.demo.jvm.heap;

/**
 *  * -Xmx1G -Xms1G -XX:+PrintGCDetails -XX:-DoEscapeAnalysis -XX:-EliminateAllocations
 * @Author: wangd
 * @Date: 2023/5/12 10:50
 *
 * result:
 * -Xmx1G -Xms1G -XX:+PrintGCDetails -XX:-DoEscapeAnalysis -XX:-EliminateAllocations  堆上创建了User对象1000万
 * -Xmx1G -Xms1G -XX:+PrintGCDetails -XX:-DoEscapeAnalysis -XX:+EliminateAllocations  堆上创建了User对象1000万
 * -Xmx1G -Xms1G -XX:+PrintGCDetails -XX:+DoEscapeAnalysis -XX:-EliminateAllocations  堆上创建了User对象1000万
 * -Xmx1G -Xms1G -XX:+PrintGCDetails -XX:+DoEscapeAnalysis -XX:+EliminateAllocations  堆上创建的User对象远低于1000万
 *
 * -Xmx100m -Xms100m -XX:+PrintGCDetails -XX:+DoEscapeAnalysis -XX:-EliminateAllocations  发生GC 堆上创建了User对象小于1000万
 * -Xmx100m -Xms100m -XX:+PrintGCDetails -XX:-DoEscapeAnalysis -XX:+EliminateAllocations  发生GC 堆上创建了User对象小于1000万
 * -Xmx100m -Xms100m -XX:+PrintGCDetails -XX:+DoEscapeAnalysis -XX:+EliminateAllocations  未发生GC 堆上创建了User对象小于1000万
 */
public class StackAllocation {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        // 查看执行时间
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为： " + (end - start) + " ms");
        // 为了方便查看堆内存中对象个数，线程sleep
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private static void alloc() {
        User user = new User();//未发生逃逸
    }

    static class User {

    }
}
