package com.wd.demo.design.p07_bridge.channel;

import com.wd.demo.design.p07_bridge.paymode.IPayMode;

import java.math.BigDecimal;

/**
 * @Author: wangd
 * @Date: 2023/4/28 12:00
 */
public abstract class Pay {

    IPayMode payMode;

    public Pay(IPayMode payMode) {
        this.payMode = payMode;
    }

    public abstract void transfer(String from, String to, BigDecimal money);
}
