package com.wd.demo.base;


import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangd
 * @Date: 2023/6/8 22:38
 */
public class MapTreeify {

    static class Point {
        int x;

        public Point(int x) {
            this.x = x;
        }

        @Override
        public int hashCode() {
            return x % 100;
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
    }


    public static void main(String[] args) {
        int a = 0, b = 3;
        System.out.println(a == b);
        Point p1 = new Point(1);
        Point p2 = new Point(101);
        System.out.println(p1 == p2);
        System.out.println(p1.equals(p2));

        Map<Point, Integer> map = new HashMap<>();
        for (int i = 1; i <= 80; i++) {
            map.put(new Point(i), i);
        }

        map.put(new Point(105), 1);
        map.put(new Point(205), 1);
        map.put(new Point(305), 1);
        map.put(new Point(405), 1);
        map.put(new Point(505), 1);
        map.put(new Point(605), 1);
        map.put(new Point(705), 1);
        map.put(new Point(805), 1);
        map.put(new Point(905), 1);

        System.out.println(map);
    }

}
