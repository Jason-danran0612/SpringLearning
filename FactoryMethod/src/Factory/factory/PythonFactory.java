package Factory.factory;

import Factory.Entity.PythonCourse;
import Factory.IEntity.ICourse;
import Factory.IFactory.ICourseFactory;

/**
 * @Classname PythonFactory
 * @Description TODO
 * @Date 2020/3/28 18:22
 * @Created by ASUS
 */
public class PythonFactory implements ICourseFactory {
    /**
     * 功能描述：创建课程实例对象，工厂方法模式
     * Author Jason
     * Date 2020-03-28 18:12
     */
    @Override
    public ICourse createCourse() {
        return new PythonCourse();
    }
}
