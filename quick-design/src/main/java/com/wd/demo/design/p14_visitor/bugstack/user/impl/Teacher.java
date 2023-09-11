package com.wd.demo.design.p14_visitor.bugstack.user.impl;

import com.wd.demo.design.p14_visitor.bugstack.user.User;
import com.wd.demo.design.p14_visitor.bugstack.visitor.Visitor;

import java.math.BigDecimal;

/**
 * @Author: wangd
 * @Date: 2023/5/2 17:33
 */
public class Teacher extends User {

    public Teacher(String name, String identity, String clazz) {
        super(name, identity, clazz);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public double entranceRatio() {
        // System.out.println("升本率：");
        return BigDecimal.valueOf(Math.random() * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
