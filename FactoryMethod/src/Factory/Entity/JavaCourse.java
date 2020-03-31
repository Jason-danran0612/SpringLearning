package Factory.Entity;

import Factory.IEntity.ICourse;
import Factory.IEntity.IExam;

/**
 * @Classname JavaCourse
 * @Description TODO
 * @Date 2020/3/28 17:21
 * @Created by ASUS
 */
public class JavaCourse implements ICourse , IExam {
    /**
     * 功能描述：课程的学学习方法
     * Author Jason
     * Date 2020-03-28 17:20
     */
    @Override
    public void Learn() {
        System.out.println("Learning the java course...");
    }

    @Override
    public void takeExam() {
        System.out.println("java考试.....");
    }
}
