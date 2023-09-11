package com.wd.demo.base;

import java.time.LocalDateTime;

/**
 * @Author: wangd
 * @Date: 2023/9/5 15:58
 */
public class Java8Time {

    public static void main(String[] args) {
        LocalDateTime now =  LocalDateTime.now();     //Current Date and Time
        LocalDateTime sameDayNextMonth = now.plusMonths(-7);
        System.out.println("sameDayNextMonth = " + sameDayNextMonth);
    }
}
