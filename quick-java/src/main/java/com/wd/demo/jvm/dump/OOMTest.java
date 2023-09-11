package com.wd.demo.jvm.dump;

import java.util.ArrayList;
import java.util.Random;

/**
 * -Xms60m -Xmx60m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:/dump/oom1.hprof
 * @author shkstart  shkstart@126.com
 * @create 2020  21:12
 */
public class OOMTest {
    public static void main(String[] args) {
        ArrayList<Picture> list = new ArrayList<>();
        while(true){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(100 * 50)));
        }
    }
}

class Picture{
    private byte[] pixels;

    public Picture(int length) {
        this.pixels = new byte[length];
    }
}
