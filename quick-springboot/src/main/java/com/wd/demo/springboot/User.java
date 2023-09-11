package com.wd.demo.springboot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author: wangd
 * @Date: 2023/6/18 15:05
 */
public class User  {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // public void setName(String name) {
    //     this.name = name;
    // }

    public static void main(String[] args) {
        LinkedList<Integer> path = new LinkedList<>();
        ArrayList<Integer> integers = new ArrayList<>(path);
        System.out.println("abc".substring(2,3));
    }
}
