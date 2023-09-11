package com.wd.demo.design.p12_template;

/**
 * @Author: wangd
 * @Date: 2023/5/1 15:24
 */
public class PureSoyaMilk extends SoyaMilk{
    @Override
    void addCondiments() {

    }

    @Override
    boolean customerWantsCondiments() {
        return false;
    }
}
