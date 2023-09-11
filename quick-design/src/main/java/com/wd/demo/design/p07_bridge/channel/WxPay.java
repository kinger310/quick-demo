package com.wd.demo.design.p07_bridge.channel;

import com.wd.demo.design.p07_bridge.paymode.IPayMode;

import java.math.BigDecimal;

/**
 * @Author: wangd
 * @Date: 2023/4/28 12:00
 */
public class WxPay extends Pay {


    public WxPay(IPayMode payMode) {
        super(payMode);
    }

    @Override
    public void transfer(String from, String to, BigDecimal money) {
        payMode.security();
        System.out.println("wxpay transfer money");
    }
}
