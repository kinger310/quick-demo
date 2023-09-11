package com.wd.demo.juc2.vlt;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;

/**
 * @Author: wangd
 * @Date: 2023/6/13 12:15
 */
@JCStressTest
@Outcome(id = {"0, 0", "1, 1", "0, 1"}, expect = Expect.ACCEPTABLE)
@Outcome(id = {"1, 0"}, expect = Expect.ACCEPTABLE_INTERESTING)
@State
public class ReOrderTest {
    int x;
    int y;

    @Actor
    public void actor1() {
        x = 1;
        y = 1;
    }

    @Actor
    public void actor2(II_Result r) {
        r.r1 = y;
        r.r2 = x;
    }

}
