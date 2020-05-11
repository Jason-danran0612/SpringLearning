package com.Test;

import com.entity.EnumSingleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Classname EnumTest
 * @Description TODO
 * @Date 2020/4/4 14:23
 * @Created by ASUS
 */
public class EnumTest {
    public static void main(String[] args) {
//        testEnum();
        testEnum1();
    }
    // 反射不会破坏枚举型单例
    /*
     * 源码分析：Constructor.java
     * T newInstanceWithCaller()
     * if ((clazz.getModifiers() & Modifier.ENUM) != 0)
     *      throw new IllegalArgumentException("Cannot reflectively create enum objects");
     * 不允许通过反射的方式创建枚举类型
     * 意思是在newInstance()的方法中做了强制性的判断，如果修饰符是Modifier.ENUM的枚举类型，
     * 直接抛出IllegalArgumentException异常
     */
    private static void testEnum1() {
        try {
            Class<?> clazz = Class.forName("com.entity.EnumSingleton");
            Constructor<?> constructor = null;
            try {
                constructor = clazz.getConstructor();//报错，无法得到构造方法，后面进行强行获取
            }catch (NoSuchMethodException e){
                System.err.println("clazz.getConstructor()====" + e.toString());
                try {
                    System.out.println("强行获取构造方法");
                    constructor = clazz.getDeclaredConstructor(String.class,int.class);//强行获取
                }catch (NoSuchMethodException e1){
                    System.err.println("clazz.getDeclaredConstructor:" + e1.toString());
                }
            }
            if(constructor != null){
                constructor.setAccessible(true);//打开权限
                System.out.println("获得构造函数权限");
                EnumSingleton enumSingleton_1 = (EnumSingleton) constructor.newInstance();
                EnumSingleton enumSingleton_2 = (EnumSingleton) constructor.newInstance();
                System.out.println(enumSingleton_1);
                System.out.println(enumSingleton_2);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //    序列化不会破坏枚举型单例
    /*
     * 源码分析：Enum.valueOf()方法
     * public static <T extends Enum<T>> T valueOf(Class<T> enumType,String name)
     * 返回具有指定名称的指定枚举类型的枚举常量。 该名称必须与用于声明此类型的枚举常量的标识符完全一致。
     * 其实就是枚举类型其实通过类名和类对象类找到一个唯一的枚举对象，所以枚举对象是不会被类加载器加载多次。
     */
    private static void testEnum() {
        EnumSingleton enumSingleton_1 ;
        EnumSingleton enumSingleton_2 = EnumSingleton.getInstance();
        enumSingleton_2.setData(new Object());
        try {
            OutputStream os = new FileOutputStream(new File("config/EnumTest.obj"));
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(enumSingleton_2);//写对象
            oos.flush();
            oos.close();
            os.close();
            InputStream is = new FileInputStream(new File("config/EnumTest.obj"));
            ObjectInputStream ois = new ObjectInputStream(is);
            enumSingleton_1 = (EnumSingleton) ois.readObject();//读对象
            System.out.println(enumSingleton_1.getData());
            System.out.println(enumSingleton_2.getData());
            System.out.println(enumSingleton_1 == enumSingleton_2);//true
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
