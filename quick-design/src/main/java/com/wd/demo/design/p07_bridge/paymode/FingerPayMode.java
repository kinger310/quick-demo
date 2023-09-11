package com.wd.demo.design.p07_bridge.paymode;

/**
 * @Author: wangd
 * @Date: 2023/4/28 12:01
 */
public class FingerPayMode implements IPayMode {
    @Override
    public void security() {
        System.out.println("校验指纹。。。");
    }
}
