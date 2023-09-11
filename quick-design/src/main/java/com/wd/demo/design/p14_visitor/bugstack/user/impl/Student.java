package com.wd.demo.design.p14_visitor.bugstack.user.impl;

import com.wd.demo.design.p14_visitor.bugstack.user.User;
import com.wd.demo.design.p14_visitor.bugstack.visitor.Visitor;

import java.util.Random;

/**
 * @Author: wangd
 * @Date: 2023/5/2 17:27
 */
public class Student extends User {
    public Student(String name, String identity, String clazz) {
        super(name, identity, clazz);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int ranking() {
        return (int) (Math.random() * 100);
    }

    public int count() {
        return 105 - new Random().nextInt(10);
    }

}
