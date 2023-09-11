package com.wd.demo.redis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @Author: wangd
 * @Date: 2023/6/13 22:42
 */
public class JedisDemo {

    public static void main(String[] args) {
        // Jedis jedis = new Jedis("192.168.0.13", 6379);
        Jedis jedis = new Jedis("192.168.128.128", 6379);
        String pong = jedis.ping();
        System.out.println("pong = " + pong);
    }

    @Test
    public void demo1() {
        Jedis jedis = new Jedis("192.168.128.128", 6379);
        // jedis.set("user", "wd");
        
        
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println("key = " + key);
            System.out.println(jedis.exists(key));
            System.out.println(jedis.ttl(key));
            System.out.println(jedis.get(key));

        }
    }

}
