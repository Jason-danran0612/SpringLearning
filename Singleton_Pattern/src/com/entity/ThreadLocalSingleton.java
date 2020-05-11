package com.entity;

/**
 * @Classname ThreadLocalSingleton
 * @Description TODO
 * @Date 2020/4/4 17:05
 * @Created by ASUS
 * 线程单例的ThreadLocalSingleton实现
 * 在主线程中无论怎样调用，都是同一个对象。
 * ThreadLocal不能保证在全局里对象唯一，但是对单线程是唯一的，并且
 * 它是天生线程安全的，ThreadLocal将所有的对象放在ThreadLocMap中，
 * 为每个线程提供一个对象，实际上是用空间换取时间来实现线程隔离的
 */
public class ThreadLocalSingleton {
    private static final ThreadLocal<ThreadLocalSingleton> THREAD_LOCAL =
            new ThreadLocal<>(){
                @Override
                protected ThreadLocalSingleton initialValue(){
                    return new ThreadLocalSingleton();
                }
            };
    private ThreadLocalSingleton(){}
    public static ThreadLocalSingleton getInstance(){
        return THREAD_LOCAL.get();
    }
}
