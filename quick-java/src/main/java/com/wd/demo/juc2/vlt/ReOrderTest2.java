package com.wd.demo.juc2.vlt;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;

/**
 * @Author: wangd
 * @Date: 2023/6/13 12:15
 */
// @JCStressTest
// @Outcome(id = {"0"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "wrong result") // 描述测试结果
// @Outcome(id = {"-1", "5"}, expect = Expect.ACCEPTABLE, desc = "normal result") // 描述测试结果
// @State
public class ReOrderTest2 {
    private  boolean flag ;
    private /*volatile */int x;

    public ReOrderTest2() {}

    @Actor
    public void actor1(I_Result r) {
        if (flag) {
            r.r1 = x;
        } else {
            r.r1 = -1;
        }
    }

    @Actor
    public void actor2(I_Result r) {
        x = 5;
        flag = true;
    }
}
