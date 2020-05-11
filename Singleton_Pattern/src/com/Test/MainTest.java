package com.Test;

import com.entity.LazyInnerSingleton;
import com.entity.LazyInnerSingleton_1;
import com.threadEntity.TestThread;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Classname MainTest
 * @Description TODO
 * @Date 2020/4/3 11:52
 * @Created by ASUS
 */
public class MainTest {
    public static void main(String[] args) {
        TestLazySingleton();
//        TestLazyInner();
//        DisturbLazyInner();//结果为false
//        DisturbLazyInner1();
//        DisturbLazyInner2();
    }
//  序列化破坏单例
    private static void DisturbLazyInner2() {
        LazyInnerSingleton_1 lazyInnerSingleton_1 = LazyInnerSingleton_1.getInstance();
        LazyInnerSingleton_1 lazyInnerSingleton_12 = null;
        try {
            OutputStream os = new FileOutputStream(new File("config/test.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(lazyInnerSingleton_1);
            oos.flush();
            oos.close();
            os.close();
            InputStream is = new FileInputStream(new File("config/test.txt"));
            ObjectInputStream ois = new ObjectInputStream(is);
            //read the object in the test.txt
            lazyInnerSingleton_12 = (LazyInnerSingleton_1) ois.readObject();
            ois.close();
            is.close();//close the stream
//            lazyInnerSingleton_1与lazyInnerSingleton_12是两个不同的对象，
//            但拥有同一静态实例对象  private static final LazyInnerSingleton_1 INSTANCE
            System.out.println(lazyInnerSingleton_1 == lazyInnerSingleton_12.getInstance());//true
            System.out.println(lazyInnerSingleton_1 == lazyInnerSingleton_12);//true
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
//  反射破坏单例解决方案
    private static void DisturbLazyInner1() {
        Object o1 = null;
        Object o2 = null;
        Class<?> clazz = LazyInnerSingleton_1.class;//get the class object
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(null);
            constructor.setAccessible(true);//open the access of the private constructor function
            //create 2 different objects
            o1 = constructor.newInstance();
            o2 = constructor.newInstance();
            System.out.println(o1 == o2);
        }catch (RuntimeException | InvocationTargetException e){
            //entering the protected codes
            System.out.println(e.toString());
            o2 = o1;
            System.out.println(o1 == o2);//true
        }catch (NoSuchMethodException  | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //  反射破坏单例
    private static void DisturbLazyInner() {
        Class<?> clazz = LazyInnerSingleton.class;
        try {
            Constructor<?> constructor;
            //获取构造方法
            constructor = clazz.getDeclaredConstructor( null);
            constructor.setAccessible(true);//强行攻入，打开访问权限
            Object o1 = constructor.newInstance();//反射调用构造方法
            Object o2 = constructor.newInstance();
            System.out.println(o1 == o2);//两个对象实例并不一致，即是单例被破坏
        } catch (NoSuchMethodException | InstantiationException |
                IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void TestLazyInner() {
        System.out.println("内部类的方式规避缺点：");
        Thread thread1 = new Thread(new TestThread());
        Thread thread2 = new Thread(new TestThread());
        thread1.start();;
        thread2.start();
    }

    //  懒汉式单例模式测试方法
    private static void TestLazySingleton() {
        System.out.println("懒汉式单例模式测试线程安全：");
        Thread thread1 = new Thread(new TestThread());
        Thread thread2 = new Thread(new TestThread());
        Thread thread3 = new Thread(new TestThread());
        Thread thread4 = new Thread(new TestThread());
        thread1.start();
        thread2.start();
        thread3.start();;
        thread4.start();
        System.out.println("测试结束");
        /*
        没有进行线程安全处理措施的结果
        懒汉式单例模式测试线程安全：
        测试结束
        Thread-1:com.entity.LazySingleton@51f8983
        Thread-3:com.entity.LazySingleton@275b3a32
        Thread-2:com.entity.LazySingleton@275b3a32
        Thread-0:com.entity.LazySingleton@275b3a32
         */
    }

}
