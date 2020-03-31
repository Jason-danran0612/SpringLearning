package Factory.IFactory;

import Factory.IEntity.ICourse;
import Factory.IEntity.IHomework;
import Factory.IEntity.IVideo;

/**
 * @Classname ICourseFactory_1
 * @Description TODO
 * @Date 2020/3/29 21:40
 * @Created by ASUS
 */
public interface ICourseFactory_1 {
    /**
     *功能描述：创建课程的实例
     *Author Jason
     *Date 2020-03-29 21:42
     */
    ICourse createCourse();
    /**
     *功能描述：创建课程视频
     *Author Jason
     *Date 2020-03-29 21:47
     */
    IVideo createVideo();
    /**
     *功能描述：做作业
     *Author Jason
     *Date 2020-03-29 21:47
     */
    IHomework createHomework();
}
