package Factory.Entity;

import Factory.IEntity.ICourse;

/**
 * @Classname PythonCourse
 * @Description TODO
 * @Date 2020/3/28 17:22
 * @Created by ASUS
 */
public class PythonCourse implements ICourse {
    /**
     * 功能描述：课程的学学习方法
     * Author Jason
     * Date 2020-03-28 17:20
     */
    @Override
    public void Learn() {
        System.out.println("Learning the Python course...");
    }
}
