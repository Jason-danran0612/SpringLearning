package com.entity;

/**
 * @Classname HungrySingleton 饿汉式单例模式
 * @Description TODO
 * @Date 2020/4/3 11:54
 * @Created by ASUS
 */
public class HungrySingleton {
//    该类在加载的时候就被实例化，绝对的线程安全，在线程出现以前就实例化，不存在访问安全问题
//    执行效率高不用加锁，但是容易浪费内存，即该对象实例没有实际用途
//    private final static HungrySingleton HUNGRY_SINGLETON = new HungrySingleton();
    private final static HungrySingleton HUNGRY_SINGLETON ;
    static {
        HUNGRY_SINGLETON = new HungrySingleton();
    }
    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return HUNGRY_SINGLETON;
    }
}
