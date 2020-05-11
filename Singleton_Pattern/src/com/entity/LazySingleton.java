package com.entity;

/**
 * @Classname LazySingleton 懒汉式单例模式，线程不安全
 * @Description TODO
 * @Date 2020/4/3 12:02
 * @Created by ASUS
 */
public class LazySingleton {
    private static LazySingleton lazySingleton = null;
    private LazySingleton(){}
    //存在线程隐患的方式
    /*public static LazySingleton getInstance(){
        if(lazySingleton == null){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }*/
    //添加锁之后，可以解决线程不安全的问题，但是当线程的
    //数量多的时候，资源被锁住之后，可能会导致较多的线程
    //阻塞，进而会降低程序的性能
    public synchronized static LazySingleton getInstance(){
        if(lazySingleton == null){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
//    方法内部加锁
//    synchronized介绍：https://blog.csdn.net/soul0928/article/details/100856712
//    https://www.jianshu.com/p/4c1ed2048985
    public static LazySingleton getInstance1(){
        if(lazySingleton == null){
            synchronized (LazySingleton.class){
                if(lazySingleton == null){
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }
}
