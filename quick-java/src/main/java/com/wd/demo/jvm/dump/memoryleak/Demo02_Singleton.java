package com.wd.demo.jvm.dump.memoryleak;

/**
 * @Author: wangd
 * @Date: 2023/5/16 15:11
 * -Xms10m -Xmx10m -XX:+PrintGCDetails
 *
 * 如果单例对象持有外部对象的引用，这个外部对象不会被回收
 */
public class Demo02_Singleton {
    private static final Demo02_Singleton instance = new Demo02_Singleton();
    private byte[] externalObject;

    private Demo02_Singleton() {
        // Private constructor to prevent instantiation from outside the class
    }

    public static Demo02_Singleton getInstance() {
        return instance;
    }

    public byte[] getExternalObject() {
        return externalObject;
    }

    public void setExternalObject(byte[] externalObject) {
        this.externalObject = externalObject;
    }

    public static void main(String[] args) throws InterruptedException {
        foo();
        System.gc();
        Thread.sleep(100);
        Demo02_Singleton.getInstance().setExternalObject(null);
        System.gc();

    }

    private static void foo() {
        Demo02_Singleton singleton = Demo02_Singleton.getInstance();
        singleton.setExternalObject(new byte[2*1024*1024]);
    }
}
