package com.wd.demo.design.p12_template;

/**
 * @Author: wangd
 * @Date: 2023/5/1 15:18
 */
public abstract class SoyaMilk {

    final void make() {
        select();
        if (customerWantsCondiments()) {
            addCondiments();
        }
        soak();
        beat();
    }

    boolean customerWantsCondiments() {
        return true;
    }

    void select() {
        System.out.println("第一步：选择好的新鲜黄豆  ");
    }

    abstract void addCondiments();

    void soak() {
        System.out.println("第三步， 黄豆和配料开始浸泡， 需要3小时 ");
    }

    void beat() {
        System.out.println("第四步：黄豆和配料放到豆浆机去打碎  ");
    }
}
