package com.wd.demo.design.p11_flyweight;

/**
 * @Author: wangd
 * @Date: 2023/4/28 17:56
 */
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
