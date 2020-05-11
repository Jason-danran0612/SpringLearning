package com.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname ContainerSingleton
 * @Description TODO
 * @Date 2020/4/4 16:08
 * @Created by ASUS
 * 注册单例的另一种写法：容器单例模式,非线程安全，方便管理
 * Spring中的容器单例模式就是这种方式
 */
public class ContainerSingleton {
    private ContainerSingleton(){}
    //支持检索的完全并发性和更新的高预期并发性的哈希表。
    //创建一个新的，空的地图与默认的初始表大小（16）。
    private static Map<String,Object> Ioc = new ConcurrentHashMap<>();//作为单例的容器
    public static Object getBean(String className){
        synchronized (Ioc){
            if(!Ioc.containsKey(className)){
                Object obj = null;
                try{
                    obj = Class.forName(className).getConstructor().newInstance();
                    Ioc.put(className,obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return obj;
            }else {
                return Ioc.get(className);
            }
        }
    }
}
