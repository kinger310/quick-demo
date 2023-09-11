package com.wd.demo.design.p11_flyweight;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: wangd
 * @Date: 2023/4/28 17:56
 */
public class WebSiteFactory {
    private Map<String, ConcreteWebSite> pool = new HashMap<>();
    // private volatile Map<String, ConcreteWebSite> pool = new ConcurrentHashMap<>();

    private final Object lock = new Object();

    public WebSiteFactory() {
    }

    public WebSite getWebSiteCategory(String type) {
        if (!this.pool.containsKey(type)) {
            synchronized (lock) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (!this.pool.containsKey(type)) {
                    System.out.println("i am putting my website");
                    this.pool.put(type, new ConcreteWebSite(type));
                }
            }
        }
        return this.pool.get(type);
    }

    public int getWebSiteCount() {
        return this.pool.size();
    }


    public static void main(String[] args) {
        WebSiteFactory factory = new WebSiteFactory();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> factory.getWebSiteCategory("search"));
            t.start();
        }
    }
}
