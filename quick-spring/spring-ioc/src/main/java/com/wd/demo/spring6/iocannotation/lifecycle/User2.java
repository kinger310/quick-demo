package com.wd.demo.spring6.iocannotation.lifecycle;

import com.wd.demo.spring6.iocannotation.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: wangd
 * @Date: 2023/6/1 18:00
 */
@Component
public class User2 {

    private User user;

    public User2() {
        System.out.println("1 调用无参构造器");
    }

    @Autowired
    public void setUser(User user) {
        System.out.println("2 给Bean对象设置属性值");
        this.user = user;
    }

    @PostConstruct
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
