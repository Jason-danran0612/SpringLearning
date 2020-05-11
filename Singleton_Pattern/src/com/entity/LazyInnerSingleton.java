package com.entity;

/**
 * @Classname LazyInnerSingleton
 * @Description TODO
 * @Date 2020/4/3 16:03
 * @Created by ASUS
 * 使用内部类的形式避免饿汉式单例模式的内存浪费以及
 * synchronized所带来的性能降低两个缺点
 */
public class LazyInnerSingleton {
    private LazyInnerSingleton(){}
//    当使用该类的getInstance()方法时，内部类才会加载，而内部类中的静态属性
//    唯一存在且不可更改，保证了单一实例，不会因为线程安全问题导致数据被污染
    public static final LazyInnerSingleton getInstance(){
//        在返回之前，内部类会先加载
        return InnerClass.instance;
    }
//    默认不加载
    private static class InnerClass{
//        实例唯一且不可更改
        private static final LazyInnerSingleton instance = new LazyInnerSingleton();
    }
}
