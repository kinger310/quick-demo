package com.wd.demo.design.p07_bridge;

import com.wd.demo.design.p07_bridge.channel.AliPay;
import com.wd.demo.design.p07_bridge.channel.Pay;
import com.wd.demo.design.p07_bridge.channel.WxPay;
import com.wd.demo.design.p07_bridge.paymode.FingerPayMode;
import com.wd.demo.design.p07_bridge.paymode.PasswdPayMode;

import java.math.BigDecimal;

/**
 * @Author: wangd
 * @Date: 2023/4/28 12:00
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("\r\n模拟测试场景；微信支付、密码方式。");
        Pay wxPay = new WxPay(new PasswdPayMode());
        wxPay.transfer("weixin_1092033111", "100000109893", new BigDecimal(100));

        System.out.println("\r\n模拟测试场景；支付宝支付、指纹方式。");
        Pay zfbPay = new AliPay(new FingerPayMode());
        zfbPay.transfer("jlu19dlxo111", "100000109894", new BigDecimal(100));

    }
}
