package Factory.factory;

import Factory.Entity.JavaCourse;
import Factory.IEntity.ICourse;
import Factory.IFactory.ICourseFactory;

/**
 * @Classname JavaFactory
 * @Description TODO
 * @Date 2020/3/28 18:21
 * @Created by ASUS
 */
public class JavaFactory implements ICourseFactory {
    /**
     * 功能描述：创建课程实例对象，工厂方法模式
     * Author Jason
     * Date 2020-03-28 18:12
     */
    @Override
    public ICourse createCourse() {
        return  new JavaCourse();
    }
}
