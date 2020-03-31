package Factory.factory;

import Factory.Entity.JavaCourse;
import Factory.Entity.JavaHomework;
import Factory.Entity.JavaVideo;
import Factory.IEntity.ICourse;
import Factory.IEntity.IHomework;
import Factory.IEntity.IVideo;
import Factory.IFactory.ICourseFactory_1;

/**
 * @Classname JavaFactory_1
 * @Description TODO
 * @Date 2020/3/29 21:49
 * @Created by ASUS
 */
public class JavaFactory_1 implements ICourseFactory_1 {
    /**
     * 功能描述：创建课程的实例
     * Author Jason
     * Date 2020-03-29 21:42
     */
    @Override
    public ICourse createCourse() {
        return new JavaCourse();
    }
    /**
     * 功能描述：创建课程视频
     * Author Jason
     * Date 2020-03-29 21:47
     */
    @Override
    public IVideo createVideo() {
        return new JavaVideo();
    }
    /**
     * 功能描述：做作业
     * Author Jason
     * Date 2020-03-29 21:47
     */
    @Override
    public IHomework createHomework() {
        return new JavaHomework();
    }
}
