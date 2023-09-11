package com.wd.demo.design.p00_principle;

import java.util.*;

// LSP  里氏替换原则。所有引用父类的地方，替换为子类也不会出现任何错误或异常
// 覆盖或实现父类的方法时输入参数可以被放大
public class LSPDemo {

    static class Father {
        public Collection doSomething(HashMap map) {
            System.out.println("父类被执行...");
            return map.values();
        }
    }

    static class Son extends Father {
        // 放大输入参数类型
        // 注意，这里不是覆写override，而是重载overload
        // public Collection doSomething(Map map) {
        //     System.out.println("子类被执行...");
        //     return map.values();
        // }

        public Collection doSomething(LinkedHashMap map) {
            System.out.println("子类被执行...");
            return map.values();
        }
    }

    public static void main(String[] args) {
        Father f = new Father();
        f.doSomething(new HashMap());
        f.doSomething(new LinkedHashMap());
        Son s = new Son();
        s.doSomething(new HashMap());
        s.doSomething(new LinkedHashMap());
        // s.doSomething(new TreeMap());
    }

}
