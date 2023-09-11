package com.wd.demo.jvm.dump.memoryleak;

/**
 * @Author: wangd
 * @Date: 2023/5/16 16:18
 * -Xms10m -Xmx10m -XX:+PrintGCDetails
 * 内部类持有外部类可能会造成内存泄漏，因为内部类的实例会持有外部类的引用，如果外部类实例被销毁了，
 * 但是内部类实例仍然存在，就会造成内存泄漏。
 * 解决方法包括：
 * 将内部类声明为静态类，这样就不会持有外部类的引用。
 * 在内部类中使用弱引用（WeakReference）或软引用（SoftReference）来引用外部类实例，这样即使外部类实例被销毁了，也不会造成内存泄漏。
 * 在外部类销毁时，手动将内部类实例设为null，这样就可以释放内存。
 * 将内部类的生命周期与外部类的生命周期保持一致，确保内部类实例在外部类实例被销毁时也会被销毁。
 */
public class Demo03_OuterClass_1 {
    private static byte[] outerData;

    // public OuterClass03_1(byte[] data) {
    //     outerData = data;
    // }

    public static void setOuterData(byte[] outerData) {
        Demo03_OuterClass_1.outerData = outerData;
    }

    public static class InnerClass {
        byte[] innerData = outerData;

        public void printOuterData() {
            System.out.println("Outer data from inner class: " + innerData);
        }
    }

    public static void main(String[] args) {
        InnerClass inner = getInnerClass();
        inner.printOuterData();
        System.gc();
        inner.printOuterData();

    }

    private static InnerClass getInnerClass() {
        // OuterClass03_1 outer = new OuterClass03_1(new byte[2*1024*1024]);
        Demo03_OuterClass_1.setOuterData(new byte[2*1024*1024]);
        InnerClass inner = new InnerClass();
        Demo03_OuterClass_1.setOuterData(null);
        inner.innerData = null;
        return inner;
    }


}

