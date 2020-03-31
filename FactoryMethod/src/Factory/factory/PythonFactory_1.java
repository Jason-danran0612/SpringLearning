package Factory.factory;

import Factory.Entity.PythonCourse;
import Factory.Entity.PythonHomework;
import Factory.Entity.PythonVideo;
import Factory.IEntity.ICourse;
import Factory.IEntity.IHomework;
import Factory.IEntity.IVideo;
import Factory.IFactory.ICourseFactory_1;

/**
 * @Classname PythonFactory_1
 * @Description TODO
 * @Date 2020/3/29 22:04
 * @Created by ASUS
 */
public class PythonFactory_1 implements ICourseFactory_1 {
    /**
     * 功能描述：创建课程的实例
     * Author Jason
     * Date 2020-03-29 21:42
     */
    @Override
    public ICourse createCourse() {
        return new PythonCourse();
    }

    /**
     * 功能描述：创建课程视频
     * Author Jason
     * Date 2020-03-29 21:47
     */
    @Override
    public IVideo createVideo() {
        return new PythonVideo();
    }

    /**
     * 功能描述：做作业
     * Author Jason
     * Date 2020-03-29 21:47
     */
    @Override
    public IHomework createHomework() {
        return new PythonHomework();
    }
}
