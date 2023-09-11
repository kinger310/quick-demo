package com.wd.demo.spring6;

/**
 * @Author: wangd
 * @Date: 2023/5/31 21:57
 */
public class User {

    static {
        System.out.println("User 初始化");
    }

    public User() {
        System.out.println("User 实例化");
    }

    public static void main(String[] args) {
        User user = new User();
        user.add();
    }

    public void add() {
        System.out.println("add....");
    }


}
