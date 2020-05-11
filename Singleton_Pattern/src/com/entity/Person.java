package com.entity;

/**
 * @Classname Person
 * @Description TODO
 * @Date 2020/4/4 16:45
 * @Created by ASUS
 */
public class Person {
    private int ID = 20200404;

    private String name = "武汉加油！！中国加油！！深情哀悼烈士和逝世的同胞！！";

    public Person() {
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "Date=" + getID() +
                ", Name=" + getName() +
                "hashCode:"+ hashCode()+"}";
    }
}
