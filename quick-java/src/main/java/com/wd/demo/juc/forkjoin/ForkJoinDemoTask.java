package com.wd.demo.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 利用fork join 计算 1+2+... +100
 * @Author: wangd
 * @Date: 2023/5/4 21:24
 */
public class ForkJoinDemoTask extends RecursiveTask<Integer> {
    int start;
    int end;
    int sum;

    public ForkJoinDemoTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= 10) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
        int mid = (start+end)/2;
        ForkJoinDemoTask task1 = new ForkJoinDemoTask(start, mid);
        ForkJoinDemoTask task2 = new ForkJoinDemoTask(mid+1, end);
        task1.fork();
        return task1.join() + task2.compute();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinDemoTask task = new ForkJoinDemoTask(1, 100);
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> submitted = pool.submit(task);
        System.out.println(submitted.get());
        pool.shutdown();
    }
}
