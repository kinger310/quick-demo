package com.wd.demo.juc2.atom;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author: wangd
 * @Date: 2023/5/7 11:40
 *  * 需求： 50个线程，每个线程100W次，总点赞数出来
 */
public class LongAdderDemo {
    // 化整为零 分散热点

    static class ClickNumber {
        long number = 0;
        public synchronized void clickBySync() {
            number++;
        }

        AtomicLong atomicLong = new AtomicLong();
        public void clickByAtomicLong() {
            atomicLong.getAndIncrement();
        }

        LongAdder longAdder = new LongAdder();
        public void clickByLongAdder() {
            longAdder.increment();
        }
    }

    public static final int THREAD_NUM = 50;

    public static void main(String[] args) throws InterruptedException {
        ClickNumber clickNumber = new ClickNumber();
        calcClick(clickNumber, c -> c.clickByLongAdder(), () -> clickNumber.longAdder.sum());
        calcClick(clickNumber, c -> c.clickByAtomicLong(), () -> clickNumber.atomicLong.get());
        calcClick(clickNumber, c -> c.clickBySync(), () -> clickNumber.number);
    }

    private static void calcClick(ClickNumber clickNumber, Consumer<ClickNumber> func, Supplier<Long> func2) throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000000; j++) {
                    func.accept(clickNumber);
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("(end-start) = " + (end - start) + ", result = " + func2.get());
    }


}
