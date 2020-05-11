package com.Test;

import com.entity.ContainerSingleton;
import com.entity.LazySingleton;
import com.entity.Person;

/**
 * @Classname ContainerTest
 * @Description TODO
 * @Date 2020/4/4 16:08
 * @Created by ASUS
 */
public class ContainerTest {
    public static void main(String[] args) {
        Person wuhan = (Person) ContainerSingleton.getBean("com.entity.Person");
        Person wuhan1 = (Person) ContainerSingleton.getBean("com.entity.Person");
        System.out.println(wuhan);
        System.out.println(wuhan1);
    }
}
