package com.wd.demo.reflect2;

import java.io.Serializable;

/**
 * @Author: wangd
 * @Date: 2023/4/29 15:54
 */
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物吃东西");
    }


}
