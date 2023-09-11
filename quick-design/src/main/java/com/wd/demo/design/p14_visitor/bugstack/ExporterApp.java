package com.wd.demo.design.p14_visitor.bugstack;

/**
 * @Author: wangd
 * @Date: 2023/5/2 18:01
 */
public class ExporterApp {

    public void export(Shape s) {
        Exporter exporter = new Exporter();
        exporter.export(s);
    }

    public static void main(String[] args) {
        // 这里输出的是 "导入形状"！
        new ExporterApp().export(new Dot());

        // 编译器使用静态绑定来处理重载方法
        // https://refactoringguru.cn/design-patterns/visitor-double-dispatch
        // 双分派是一个允许在重载时使用动态绑定的技巧。
    }
}

class Exporter{
    public void export(Dot d) {
        System.out.println("导出点");
    }

    public void export(Shape s) {
        System.out.println("导出形状");
    }
}

interface Graphic {
    void draw();
}

class Shape implements Graphic {
    @Override
    public void draw() {
        System.out.println("画形状");
    }
}

class Dot extends Shape {
    @Override
    public void draw() {
        System.out.println("画点");
    }
}
