package Factory.Entity;

import Factory.IEntity.IHomework;

/**
 * @Classname JavaHomework
 * @Description TODO
 * @Date 2020/3/29 21:52
 * @Created by ASUS
 */
public class JavaHomework implements IHomework {
    /**
     * 功能描述：做作业
     * Author Jason
     * Date 2020-03-29 21:44
     */
    @Override
    public void doHomework() {
        System.out.println("做Java的作业......");
    }
}
