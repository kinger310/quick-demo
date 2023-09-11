package com.wd.demo.interview.ali;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: wangd
 * @Date: 2023/7/10 19:50
 *
 * 假设本地有一个文件夹，文件夹下面有若干文件（文件数大于50小于100），文件的存储格式是文本格式（后缀名是.txt)，文件的大小每个文件不会超过100k。
 * 文件格式如下：
 * 2000102，100，98.3
 * 2000103，101，73.3
 * 2000104，102，98.3
 * 2000105，100，101.3
 * 2000106，101，45.3
 * 文件格式说明：文件每行都由三列构成，第一列是一个id，第二列是分组groupId, 第三列是指标quota。
 * id的数据类型是String, groupId的数据类型String, quota的数据类型float。
 *
 * 功能要求：
 * 1.对文件夹下面所有文件的内容进行合并排序，输出按照分组升序排序之后，每个分组下面的最小指标值。比如上面的数据输出结果为：
 * 100，2000102，98.3
 * 101，2000106，45.3
 * 102，2000104，98.3
 * 2. 采用生产者消费者模式
 * 3.生产者：文件读取要有线程池来执行，线程池的大小固定为10，多个文件内容需要存储到指定的内容数据结构当中。
 * 4.消费者：查找要求有独立线程，跟读取线程并行，直接消费读取线程池产生的内存数据结构，去重(保留同一个groupId下quota最小的行)和排序(按照groupId排序)。
 */
public class Producer {

    static class Row {
        String id;
        String groupId;
        float quota;

        public Row(String id, String groupId, float quota) {
            this.id = id;
            this.groupId = groupId;
            this.quota = quota;
        }
    }

    ConcurrentLinkedDeque<Row> q = new ConcurrentLinkedDeque<>();

    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    public static void main(String[] args) {
        Producer p = new Producer();
        p.readFiles();

        System.out.println(p.q);
        // consumeData
        // new Thread(() -> {
        //     new Consumer().consume();
        // }).start();

        // 最后hasmap<groupid, list>>
    }



    private void readFiles() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        File d = new File("D:\\codes\\quick-demo\\quick-java\\src\\main\\java\\com\\wd\\demo\\ali\\data");
        for (File file : d.listFiles()) {
            threadPool.submit(() -> {
                // read file
                try (BufferedReader r = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = r.readLine()) != null) {
                        String[] data = line.split(",");
                        q.add(new Row(data[0], data[1], Float.valueOf(data[2])));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        };
        // 信号量，先生产，后消费
    }

    private void consume() throws InterruptedException {
        // 信号量机制 notify wait
        // 保证
        // 如果q为空，wait。如果q有数据，notify
        if (q.size() == 0) {
            condition.wait();
        }


    }

}
