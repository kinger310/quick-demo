package com.wd.demo.design.p09_proxy.cglib;

class SuperMan {

    public String getBelief() {
        return "I believe I can fly";
    }

    public void eat(String food) {
        System.out.println("food = " + food);
    }
}