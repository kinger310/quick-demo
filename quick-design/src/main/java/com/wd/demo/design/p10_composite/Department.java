package com.wd.demo.design.p10_composite;

/**
 * @Author: wangd
 * @Date: 2023/4/28 16:25
 */
public class Department extends OrganizationComponent {
    public Department(String name, String des) {
        super(name, des);
    }

    public String getName() {
        return super.getName();
    }

    public String getDes() {
        return super.getDes();
    }

    protected void print() {
        System.out.println(this.getName());
    }
}
