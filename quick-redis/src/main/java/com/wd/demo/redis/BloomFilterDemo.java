package com.wd.demo.redis;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @Author: wangd
 * @Date: 2023/6/13 22:20
 */
public class BloomFilterDemo {


    @Test
    public static void main(String[] args) {
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                500,
                0.01);

        filter.put(1);
        filter.put(2);
        filter.put(3);

        Assertions.assertTrue(filter.mightContain(1));
        Assertions.assertTrue(filter.mightContain(2));
        Assertions.assertTrue(filter.mightContain(3));
        Assertions.assertFalse(filter.mightContain(100));

    }
}
