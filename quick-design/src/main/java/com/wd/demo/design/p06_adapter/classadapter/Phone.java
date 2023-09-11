package com.wd.demo.design.p06_adapter.classadapter;

/**
 * @Author: wangd
 * @Date: 2023/4/28 10:15
 */
public class Phone {
    public Phone() {
    }

    public void charging(IVoltage5V iVoltage5V) {
        if (iVoltage5V.output5V() == 5) {
            System.out.println("电压为5V, 可以充电~~");
        } else if (iVoltage5V.output5V() > 5) {
            System.out.println("电压大于5V, 不能充电~~");
        }

    }
}
