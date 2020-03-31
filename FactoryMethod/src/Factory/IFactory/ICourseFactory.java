package Factory.IFactory;

import Factory.IEntity.ICourse;

/**
 * @Classname ICourseFactory
 * @Description TODO
 * @Date 2020/3/28 18:11
 * @Created by ASUS
 */
public interface ICourseFactory {
    /**
     *功能描述：创建课程实例对象，工厂方法模式
     *Author Jason
     *Date 2020-03-28 18:12
     */
    ICourse createCourse();
}
