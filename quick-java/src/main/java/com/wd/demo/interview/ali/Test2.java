package com.wd.demo.interview.ali;

/**
 * @Author: wangd
 * @Date: 2023/7/11 0:43
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Test2 {

    public static final String path = "D:\\codes\\quick-demo\\quick-java\\src\\main\\java\\com\\wd\\demo\\ali\\data";
    private static Integer count = 0;
    private static final Integer FULL = 10;
    // 创建一个锁对象
    private final Lock lock = new ReentrantLock();
    // 创建两个条件变量，一个为缓冲区非满，一个为缓冲区非空
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    LinkedList<Row> queue = new LinkedList<>();

    ExecutorService executor = Executors.newFixedThreadPool(10);

    static TreeMap<String, PriorityQueue<Row>> map = new TreeMap<>(String::compareTo);
    static CountDownLatch countDownLatch = new CountDownLatch(getFilesCount(path));

    static volatile boolean flag = true;

    public static int getFilesCount(String folderPath) {
        int n = 0;
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isFile()) {
                    n++;
                }
            }
        }
        return n;
    }


    public static void main(String[] args) throws InterruptedException {
        Test2 test2 = new Test2();
        test2.produce(path); // 读取指定文件夹中的文件

        new Thread(test2.new Consumer()).start();
        countDownLatch.await();
        flag = false;
        Thread.sleep(100);
        System.out.println("main end");

        map.forEach((k, v) -> System.out.println(v.peek()));

    }

    public void produce(String folderPath) {
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        executor.submit(new Producer(file));
                    }
                }
            }
        }
        // flag = false;
        executor.shutdown();
    }

    class Producer implements Runnable {
        private File file;

        public Producer(File file) {
            this.file = file;
        }

        @Override
        public void run() {

            lock.lock();
            try {
                try (BufferedReader r = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = r.readLine()) != null) {
                        String[] data = line.split(",");
                        while (count == FULL) {
                            try {
                                notFull.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        queue.add(new Row(data[0], data[1], Float.valueOf(data[2])));
                        count++;
                        System.out.println(Thread.currentThread().getName()
                                + "生产者生产，目前总共有" + count);
                        // 唤醒消费者
                        notEmpty.signal();
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                lock.unlock();
            }
            countDownLatch.countDown();
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (count == 0) {
                        try {
                            notEmpty.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    Row row = queue.removeFirst();
                    if (!map.containsKey(row.groupId)) {
                        map.put(row.groupId, new PriorityQueue<>((o1, o2) -> (int) (o1.quota - o2.quota)));
                    }
                    map.get(row.groupId).add(row);
                    System.out.println("row = " + row);
                    System.out.println(Thread.currentThread().getName()
                            + "消费者消费，目前总共有" + count);
                    notFull.signalAll();
                    if (!flag && count == 0) break;
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}