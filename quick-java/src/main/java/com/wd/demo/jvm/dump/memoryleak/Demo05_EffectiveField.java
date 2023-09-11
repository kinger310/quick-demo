package com.wd.demo.jvm.dump.memoryleak;

/**
 * @Author: wangd
 * @Date: 2023/5/16 18:52
 * 5-变量不合理的作用域
 * 变量不合理的作用域。一般而言，一个变量的定义的作用范围大于其使用范围，很有可能会造成内存洲漏。另一方面，如果没有及时地把对象设置为nu11，很有可能导致内存泄漏的发生。

 * 如上面这个伪代码，通过readFromNet方法把接受的消息保存在变量msg中，然后调用saveDB方法把msg的内容保存到数据库中，
 * 此时msg已经就没用了，由于msg的生命周期与对象的生命周期相同，此时msg还不能回收，因此造成了内存泄漏。
 *
 * 实际上这个msg变量可以放在receiveMsg方法内部，当方法使用完，那么msg的生命周期也就结束,此时就可以回收了。
 * 还有一种方法，在使用完msg后，把msg设置为nul1，这样垃圾回收器也会回收msg的内存空间。
 */
public class Demo05_EffectiveField {

    private String msg;
    public void receiveMsg() {
        // 方法一：相同的作用域 private String msg;
        // readFromNet();// 从网络中接受数据保存到msg中
        // saveDB();// 把msg保存到数据库中
        // 方法二： 使用完置null  msg = null;
    }
}
