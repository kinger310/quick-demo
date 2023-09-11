package com.wd.demo.jvm.jprofiler;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wangd
 * @Date: 2023/5/16 21:38
 */
public class MemoryLeak {


    public static void main(String[] args) {
        while (true) {
            ArrayList beanList = new ArrayList();
            for (int i = 0; i < 500; i++) {
                Bean data = new Bean();
                data.list.add(new byte[1024 * 1]);//10kb
                beanList.add(data);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

 class Bean {
    int size = 10;
    String info = "hello,atguigu";
    // ArrayList<byte[]> list = new ArrayList<>();
    static ArrayList list = new ArrayList();
}
