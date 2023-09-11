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
public class Demo06_ChangeHashCode_2 {

    static class Person {
        int id;
        String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person)) return false;

            Person person = (Person) o;

            if (id != person.id) return false;
            return name != null ? name.equals(person.name) : person.name == null;
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        HashSet<Person> set = new HashSet<>();
        Person p1 = new Person(1001, "AA");
        Person p2 = new Person(1002, "BB");

        set.add(p1);
        set.add(p2);

        // 因为重写了hashcode，所以hashcode会变化
        p1.name = "CC";//导致了内存的泄漏
        set.remove(p1); //删除失败

        System.out.println(set);

        set.add(new Person(1001, "CC"));
        System.out.println(set);

        set.add(new Person(1001, "AA"));
        System.out.println(set);

    }
}
