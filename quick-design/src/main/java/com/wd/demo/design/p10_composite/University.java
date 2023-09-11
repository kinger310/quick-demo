package com.wd.demo.design.p10_composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: wangd
 * @Date: 2023/4/28 16:25
 */
public class University extends OrganizationComponent {
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public University(String name, String des) {
        super(name, des);
    }

    protected void add(OrganizationComponent organizationComponent) {
        this.organizationComponents.add(organizationComponent);
    }

    protected void remove(OrganizationComponent organizationComponent) {
        this.organizationComponents.remove(organizationComponent);
    }

    public String getName() {
        return super.getName();
    }

    public String getDes() {
        return super.getDes();
    }

    protected void print() {
        System.out.println("--------------" + this.getName() + "--------------");
        Iterator var2 = this.organizationComponents.iterator();

        while(var2.hasNext()) {
            OrganizationComponent organizationComponent = (OrganizationComponent)var2.next();
            organizationComponent.print();
        }

    }
}
