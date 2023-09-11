package com.wd.demo.jvm.classes;

import java.io.Serializable;

/**
 * @Author: wangd
 * @Date: 2023/5/29 15:21
 */

// interface AAA {
//
// }
// implements Serializable, AAA

public class Demo  {
    // public static final int NUM = 0x7fffffff;
    private int num = 1;

    public int add(){
        num = num + 2;
        return num;
    }
}

