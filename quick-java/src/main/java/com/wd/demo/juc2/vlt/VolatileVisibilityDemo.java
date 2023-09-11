package com.wd.demo.juc2.vlt;

/**
 *
 * volatile 可见性测试
 * @Author: wangd
 * @Date: 2023/5/5 8:39
 * -Xint 禁止JIT优化，也可以改变
 */
public class VolatileVisibilityDemo {

    public static void main(String[] args) {
        final VT vt = new VT();

        Thread t1 = new Thread(vt);
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignore) {
                }
                vt.sign = true;
                System.out.println("vt.sign = true 通知 while (!sign) 结束！");
            }
        });

        t1.start();
        t2.start();
    }

}

class VT implements Runnable {

    public boolean sign = false;

    public void run() {
        int i = 0;
        while (!sign) {
            i++;
        }
        System.out.println("你坏" + i);
    }
}