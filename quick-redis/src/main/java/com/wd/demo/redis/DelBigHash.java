package com.wd.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangd
 * @Date: 2023/7/16 18:10
 */
public class DelBigHash {
    public static void main(String[] args) {
        new DelBigHash().delBigHash("192.168.128.128", 6379, "customer");
    }

    public void delBigHash(String host, int port, String bigHashKey) {
        Jedis jedis = new Jedis(host, port);
        ScanParams scanParams = new ScanParams().count(100);
        String cursor = "0";
        do {
            ScanResult<Map.Entry<String, String>> hscan = jedis.hscan(bigHashKey, cursor, scanParams);
            List<Map.Entry<String, String>> result = hscan.getResult();
            for (Map.Entry<String, String> entry : result) {
                jedis.hdel(bigHashKey, entry.getKey());
            }
            cursor = hscan.getCursor();
        } while (!cursor.equals("0"));
        jedis.del(bigHashKey);
    }
}
