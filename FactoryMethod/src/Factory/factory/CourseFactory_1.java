package Factory.factory;

import Factory.IEntity.ICourse;
import Factory.Entity.JavaCourse;
import Factory.Entity.PythonCourse;

import java.lang.reflect.InvocationTargetException;


/**
 * @Classname CourseFactory
 * @Description TODO 简单工厂模式
 * @Date 2020/3/28 17:25
 * @Created by ASUS
 */
public class CourseFactory_1 {
    //简单的工厂模式，以课程的名称传入进行构建实例对象
    //相对于在客户端程序中调用而言，扩展性得到一定的提高
    //但是以后增加课程的时候，需要更改工厂方法，不符合开闭原则
    public ICourse creatCourse(String courseName){
        if("Java".equals(courseName)){
            return new JavaCourse();
        }else if("Python".equals(courseName)){
            return new PythonCourse();
        }else{
            return null;
        }
    }
    //改进的工厂方法，利用反射的技术实现
    public ICourse createCourse(Class<? extends ICourse> clazz){
        try {
            if(null != clazz) {
                return clazz.getConstructor().newInstance();//反射构造实例
            }
            //Class<T>中有getConstructor()方法，它的返回值是Constructor<T>类型对象
            //而Constructor<T>newInstance()方法返回 T 的实例对象
            //类型参数有助于编写类型安全的代码
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
