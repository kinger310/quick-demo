package com.wd.demo.design.p02_prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangd
 * @Date: 2023/4/27 21:10
 */
public class DeepCopy implements Cloneable {

    public String name;

    public List<String> friends;


    @Override
    protected DeepCopy clone() throws CloneNotSupportedException {
        return (DeepCopy) super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        DeepCopy deepCopy = new DeepCopy();
        deepCopy.name = "wd";
        deepCopy.friends = new ArrayList<>();
        deepCopy.friends.add("a");

        System.out.println(deepCopy.name.hashCode());
        DeepCopy clone = deepCopy.clone();
        deepCopy.name = "hr";
        System.out.println(deepCopy.name.hashCode());
        System.out.println(clone.name.hashCode());

        System.out.println(deepCopy.friends.hashCode());
        System.out.println(clone.friends.hashCode());

    }
}
