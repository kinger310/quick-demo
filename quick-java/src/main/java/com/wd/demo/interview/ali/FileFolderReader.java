// package com.wd.demo.ali;
//
// /**
//  * @Author: wangd
//  * @Date: 2023/7/10 23:58
//  */
// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.*;
// import java.util.concurrent.*;
// import java.util.concurrent.locks.Condition;
// import java.util.concurrent.locks.ReentrantLock;
//
// public class FileFolderReader {
//
//     private static final Integer FULL = 10;
//
//     private ExecutorService executor;
//     // public static final  ArrayBlockingQueue<Row> blockingQueue = new ArrayBlockingQueue<>(10);
//
//     static LinkedList<Row> queue = new LinkedList<>();
//
//     static ReentrantLock lock = new ReentrantLock();
//
//     //创建两个条件变量，一个为缓冲区非满，一个为缓冲区非空
//     private static final Condition notFull = lock.newCondition();
//     private static final Condition notEmpty = lock.newCondition();
//
//
//     public FileFolderReader(int numThreads) {
//         executor = Executors.newFixedThreadPool(numThreads);
//     }
//
//     public void produce(String folderPath) {
//         File folder = new File(folderPath);
//         if (folder.isDirectory()) {
//             File[] files = folder.listFiles();
//             if (files != null) {
//                 for (File file : files) {
//                     if (file.isFile()) {
//                         executor.submit(new Producer(file));
//                     }
//                 }
//             }
//         }
//         executor.shutdown();
//     }
//
//     private class Producer implements Runnable {
//         private File file;
//
//         public Producer(File file) {
//             this.file = file;
//         }
//
//         @Override
//         public void run() {
//             // 在这里处理文件的读取逻辑
//             // 可以根据需要进行具体的操作，比如读取文件内容、处理文件数据等
//             // System.out.println("Reading file: " + file.getName());
//
//             for (int i = 0; i < 10; i++) {
//                 //获取锁
//                 lock.lock();
//                 try {
//                     while (count == FULL) {
//                         try {
//                             notFull.await();
//                         } catch (InterruptedException e) {
//                             e.printStackTrace();
//                         }
//                     }
//                     count++;
//                     queue.add(row)
//                     System.out.println(Thread.currentThread().getName()
//                             + "生产者生产，目前总共有" + count);
//                     //唤醒消费者
//                     notEmpty.signal();
//                 } finally {
//                     //释放锁
//                     lock.unlock();
//                 }
//             }
//             try (BufferedReader r = new BufferedReader(new FileReader(file))) {
//                 int count = 0;
//                 String line;
//                 while ((line = r.readLine()) != null) {
//                     String[] data = line.split(",");
//                     while (count == FULL) {
//                         try {
//                             notFull.await();
//                         } catch (InterruptedException e) {
//                             e.printStackTrace();
//                         }
//                     }
//                     queue.add(new Row(data[0], data[1], Float.valueOf(data[2])));
//
//
//                 }
//             } catch (IOException e) {
//                 throw new RuntimeException(e);
//             }
//             System.out.println("blockingQueue.size() = " + queue.size());
//         }
//     }
//
//     // class Consumer implements Runnable {
//     //
//     //     @Override
//     //     public void run() {
//     //         try {
//     //             Row take = blockingQueue.take();
//     //         } catch (InterruptedException e) {
//     //             throw new RuntimeException(e);
//     //         }
//     //     }
//     // }
//
//
//     public static void main(String[] args) throws InterruptedException {
//         // FileFolderReader fileFolderReader = new FileFolderReader(10); // 创建一个包含5个线程的线程池
//         // fileFolderReader.produce("D:\\codes\\quick-demo\\quick-java\\src\\main\\java\\com\\wd\\demo\\ali\\data"); // 读取指定文件夹中的文件
//         //
//         // TreeMap<String, PriorityQueue<Row>> map = new TreeMap<>(String::compareTo);
//         //
//         // CountDownLatch count = new CountDownLatch(1);
//         //
//         // new Thread(() -> {
//         //
//         //         synchronized (lock)  {
//         //             if (queue.size() == 0) {
//         //                 try {
//         //                     notEmpty.wait();
//         //                 } catch (InterruptedException e) {
//         //                     throw new RuntimeException(e);
//         //                 }
//         //             }
//         //             Row row = queue.removeFirst();
//         //             System.out.println("row = " + row);
//         //             if (!map.containsKey(row.groupId)){
//         //                 map.put(row.groupId, new PriorityQueue<>((o1, o2) -> (int)(o1.quota - o2.quota)));
//         //             } else {
//         //                 map.get(row.groupId).add(row);
//         //             }
//         //         }
//         //
//         //
//         //     count.countDown();
//         // }).start();
//         //
//         // count.await();
//         // map.forEach((k,v) -> {
//         //     System.out.println(v.peek());
//         // });
//     }
//
// }
//
