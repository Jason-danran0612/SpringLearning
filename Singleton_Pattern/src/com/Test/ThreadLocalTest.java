package com.Test;

import com.entity.ThreadLocalSingleton;
import com.threadEntity.ExecutorThread;


/**
 * @Classname ThreadLocalTest
 * @Description TODO
 * @Date 2020/4/4 17:12
 * @Created by ASUS
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println("线程不安全实例：");
        Thread t1 = new Thread(new ExecutorThread());
        Thread t2 = new Thread(new ExecutorThread());
        Thread t3 = new Thread(new ExecutorThread());
        Thread t4 = new Thread(new ExecutorThread());
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
