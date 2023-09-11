package com.wd.demo.design.p11_flyweight;

/**
 * @Author: wangd
 * @Date: 2023/4/28 17:59
 */
public class ConcreteWebSite extends WebSite {
    private String type = "";

    public ConcreteWebSite(String type) {
        this.type = type;
    }


    public void use(User user) {
        System.out.println("网站的发布形式为:" + this.type + " 在使用中 .. 使用者是" + user.getName());
    }

}
