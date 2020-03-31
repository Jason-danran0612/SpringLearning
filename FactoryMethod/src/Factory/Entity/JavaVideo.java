package Factory.Entity;

import Factory.IEntity.IVideo;

/**
 * @Classname JavaVideo
 * @Description TODO
 * @Date 2020/3/29 21:50
 * @Created by ASUS
 */
public class JavaVideo implements IVideo {
    /**
     * 功能描述：制作课程视频
     * Author Jason
     * Date 2020-03-29 21:45
     */
    @Override
    public void makeVideo() {
        System.out.println("制作Java课程视频.......");
    }
}
