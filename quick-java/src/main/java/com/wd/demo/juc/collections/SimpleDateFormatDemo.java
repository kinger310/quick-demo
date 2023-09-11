package com.wd.demo.juc.collections;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: wangd
 * @Date: 2023/5/4 15:47
 * https://bugstack.cn/md/java/interview/2020-09-23-%E9%9D%A2%E7%BB%8F%E6%89%8B%E5%86%8C%20%C2%B7%20%E7%AC%AC12%E7%AF%87%E3%80%8A%E9%9D%A2%E8%AF%95%E5%AE%98%EF%BC%8CThreadLocal%20%E4%BD%A0%E8%A6%81%E8%BF%99%E4%B9%88%E9%97%AE%EF%BC%8C%E6%88%91%E5%B0%B1%E6%8C%82%E4%BA%86%EF%BC%81%E3%80%8B.html#%E9%9D%A2%E7%BB%8F%E6%89%8B%E5%86%8C-%C2%B7-%E7%AC%AC12%E7%AF%87%E3%80%8A%E9%9D%A2%E8%AF%95%E5%AE%98-threadlocal-%E4%BD%A0%E8%A6%81%E8%BF%99%E4%B9%88%E9%97%AE-%E6%88%91%E5%B0%B1%E6%8C%82%E4%BA%86-%E3%80%8B
 */
public class SimpleDateFormatDemo {
    private static SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                String dateStr = f.format(new Date());
                try {
                    Date parseDate = f.parse(dateStr);
                    String dateStrCheck = f.format(parseDate);
                    boolean equals = dateStr.equals(dateStrCheck);
                    if (!equals) {
                        System.out.println(equals + " " + dateStr + " " + dateStrCheck);
                    } else {
                        System.out.println(equals);
                    }
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
            }).start();
        }
    }

}
