package com.wd.demo.spring6.iocxml.lifecycle;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

/**
 * @Author: wangd
 * @Date: 2023/6/1 18:00
 */
public class User {

    private String name;

    public User() {
        System.out.println("1 调用无参构造器");
    }

    public void setName(String name) {
        System.out.println("2 给Bean对象设置属性值");
        this.name = name;
    }

    public void initMethod() {
        System.out.println("4 bean对象初始化，调用指定初始化方法");
    }

    public void run() {
        System.out.println("6 bean对象使用中");
    }

    public void destroyMethod() {
        System.out.println("7 bean对象销毁，调用指定销毁方法");
    }
}
