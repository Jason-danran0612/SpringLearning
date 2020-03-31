package Factory.Entity;

import Factory.IEntity.IVideo;

/**
 * @Classname PythonVideo
 * @Description TODO
 * @Date 2020/3/29 21:51
 * @Created by ASUS
 */
public class PythonVideo implements IVideo {
    /**
     * 功能描述：制作课程视频
     * Author Jason
     * Date 2020-03-29 21:45
     */
    @Override
    public void makeVideo() {
        System.out.println("制作Python课程视频........");
    }
}
