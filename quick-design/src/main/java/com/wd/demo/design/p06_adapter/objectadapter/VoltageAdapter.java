package com.wd.demo.design.p06_adapter.objectadapter;

/**
 * @Author: wangd
 * @Date: 2023/4/28 10:18
 */
public class VoltageAdapter implements IVoltage5V {
    private Voltage220V voltage220V;
    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        int src = this.voltage220V.output220V();
        System.out.println("使用对象适配器，进行适配~~");
        int dst = src / 44;
        System.out.println("适配完成，输出的电压为=" + dst);
        return dst;
    }
}
