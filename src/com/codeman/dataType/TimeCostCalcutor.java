package com.codeman.dataType;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间复杂度
 */
public class TimeCostCalcutor {
    public static void main(String[] args) {
//        System.out.println(t_1(10));
//        System.out.println(t_n(10));
//        List<String> strings = new ArrayList<>();
//        strings.add(null);
//        strings.add(null);
//        System.out.println(strings.size());

//        StringBuffer sb = new StringBuffer("select,");
//        System.out.println(sb);
//        sb.deleteCharAt(sb.length()-1);
//        System.out.println(sb);
        List<Person> people = new ArrayList<>();
        Person p1 = new Person("小米", 2);
        people.add(p1);
        p1 = new Person("小红", 3);
        people.add(p1);
        System.out.println(people);
    }

    /**
     * 复杂度为 O(1),仅一步即可解决问题
     */
    private static int t_1(int n) {
        int count = n * 2;
        return count;
    }

    /**
     * 复杂度为O(n),需要n步才能解决问题
     * @param n
     * @return
     */
    private static int t_n(int n) {
        int count = 0;
        for (int i= 0; i < n; i ++) {
            count += 2;
        }
        return count;
    }



}


class Person {
    private String name;
    private int age;


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}