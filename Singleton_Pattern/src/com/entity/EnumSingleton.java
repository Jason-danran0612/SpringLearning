package com.entity;

/**
 * @Classname EnumSingleton
 * @Description TODO
 * @Date 2020/4/4 14:19
 * @Created by ASUS
 * 注册单例模式（经典的单例模式实现写法）
 * 是单例模式的优雅实现
 */
public enum  EnumSingleton {
    INSTANCE;
    private Object data;
    public Object getData(){
        return this.data;
    }
    public void setData(Object data){
        this.data = data;
    }
    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
