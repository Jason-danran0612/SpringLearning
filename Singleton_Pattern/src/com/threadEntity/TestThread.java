package com.threadEntity;

import com.entity.LazyInnerSingleton;
import com.entity.LazySingleton;

/**
 * @Classname TestThread
 * @Description TODO
 * @Date 2020/4/3 12:07
 * @Created by ASUS
 */
public class TestThread implements Runnable {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     * 用于验证懒汉式单例模式的线程不安全性
     */
    @Override
    public void run() {
        LazySingleton lazySingleton = LazySingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + "Lazy:" + lazySingleton);
        LazyInnerSingleton lazyInnerSingleton = LazyInnerSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + "LazyInner:" + lazyInnerSingleton);
    }
}
