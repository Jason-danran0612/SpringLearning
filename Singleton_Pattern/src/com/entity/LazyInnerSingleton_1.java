package com.entity;

import java.io.Serializable;

/**
 * @Classname LazyInnerSingleton_1 优化后的代码
 * @Description TODO
 * @Date 2020/4/3 17:15
 * @Created by ASUS
 * 该类只允许创建一个单例的实例对象，创建多个会抛出 RuntimeException
 */
public class LazyInnerSingleton_1 implements Serializable {
    private LazyInnerSingleton_1(){
        if(InnerClass.INSTANCE != null){
            throw new RuntimeException("LazyInnerSingleton_1类已经存在一个实例化对象,不允许创建多个对象");
        }
    }
    public static final LazyInnerSingleton_1 getInstance(){
        return InnerClass.INSTANCE;
    }
    private static final class InnerClass{
//        该实例不会被序列化到文件，保存在静态区中
        private static final LazyInnerSingleton_1 INSTANCE = new LazyInnerSingleton_1();
    }
    private Object readResolve(){
        return InnerClass.INSTANCE;
    }
}
