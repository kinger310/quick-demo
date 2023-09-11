package com.wd.demo.interview.ali;

/**
 * 使用BlockingQueue实现生产者消费者模型
 * @author ZGJ
 * @date 2017年6月29日
 */
public class BlockingQueueModel {

    // static class Row {
    //     String id;
    //     String groupId;
    //     float quota;
    //
    //     public Row(String id, String groupId, float quota) {
    //         this.id = id;
    //         this.groupId = groupId;
    //         this.quota = quota;
    //     }
    // }
    //
    // private static Integer count = 0;
    //
    //
    // //创建一个阻塞队列
    // final LinkedBlockingQueue<Row> blockingQueue = new LinkedBlockingQueue<>();
    // public static void main(String[] args) {
    //     Test3 test3 = new Test3();
    //     new Thread(test3.new Producer()).start();
    //     new Thread(test3.new Consumer()).start();
    //     new Thread(test3.new Producer()).start();
    //     new Thread(test3.new Consumer()).start();
    //     new Thread(test3.new Producer()).start();
    //     new Thread(test3.new Consumer()).start();
    //     new Thread(test3.new Producer()).start();
    //     new Thread(test3.new Consumer()).start();
    // }
    // class Producer implements Runnable {
    //     @Override
    //     public void run() {
    //         for (int i = 0; i < 10; i++) {
    //             // try {
    //             //     Thread.sleep(3000);
    //             // } catch (Exception e) {
    //             //     e.printStackTrace();
    //             // }
    //             try {
    //                 blockingQueue.put(new Row());
    //                 count++;
    //                 System.out.println(Thread.currentThread().getName()
    //                         + "生产者生产，目前总共有" + count);
    //             } catch (InterruptedException e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     }
    // }
    // class Consumer implements Runnable {
    //     @Override
    //     public void run() {
    //         for (int i = 0; i < 10; i++) {
    //             try {
    //                 Thread.sleep(3000);
    //             } catch (InterruptedException e1) {
    //                 e1.printStackTrace();
    //             }
    //             try {
    //                 blockingQueue.take();
    //                 count--;
    //                 System.out.println(Thread.currentThread().getName()
    //                         + "消费者消费，目前总共有" + count);
    //             } catch (InterruptedException e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     }
    // }
}

