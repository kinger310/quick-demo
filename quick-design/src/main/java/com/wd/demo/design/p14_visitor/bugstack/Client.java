package com.wd.demo.design.p14_visitor.bugstack;

import com.wd.demo.design.p14_visitor.bugstack.visitor.impl.Parent;
import com.wd.demo.design.p14_visitor.bugstack.visitor.impl.Principal;

/**
 * @Author: wangd
 * @Date: 2023/5/2 17:30
 */
public class Client {

    public static void main(String[] args) {
        DataView dataView = new DataView();
        dataView.show(new Parent());
        System.out.println("==============================");
        dataView.show(new Principal());
    }
}
