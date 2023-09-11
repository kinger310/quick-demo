package com.wd.demo.jvm.dump.memoryleak;

import java.util.HashSet;

/**
 * @Author: wangd
 * @Date: 2023/5/16 19:03
 *
 * 6- 改变哈希值
 * 改变哈希值，当一个对象被存储进HashSet集合中以后，就不能修改这个对象中的那些参与计算哈希值的字段了。
 * 否则，对象修改后的哈希值与最初存储进HashSet集合中时的哈希值就不同了，
 * 在这种情况下，即使在contains方法使用该对象的当前引用作为的参数去HashSet集合中检索对象，
 * 也将返回找不到对象的结果，这也会导致无法从HashSet集合中单独删除当前对象，造成内存泄漏。
 * 这也是 String 为什么被设置成了不可变类型，我们可以放心地把 String 存入 HashSet，或者把String 当做 HashMap 的 key 值;
 * 当我们想把自己定义的类保存到散列表的时候，需要保证对象的 hashCode 不可变
 */
public class Demo06_ChangeHashCode_1 {

    static class Point {
        int x;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Point other = (Point) obj;
            if (x != other.x) return false;
            return true;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    '}';
        }
    }

    public static void main(String[] args) {
        HashSet<Point> hs = new HashSet<Point>();
        Point cc = new Point();
        cc.setX(10);//hashCode = 41
        hs.add(cc);

        cc.setX(20);//hashCode = 51  此行为导致了内存的泄漏

        System.out.println("hs.remove = " + hs.remove(cc));//false
        hs.add(cc);
        System.out.println("hs.size = " + hs.size());//size = 2

        System.out.println(hs);

        System.gc();
    }

}
