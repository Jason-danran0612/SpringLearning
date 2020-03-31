package Factory.Entity;

import Factory.IEntity.IHomework;

/**
 * @Classname PythonHomework
 * @Description TODO
 * @Date 2020/3/29 21:54
 * @Created by ASUS
 */
public class PythonHomework implements IHomework {
    /**
     * 功能描述：做作业
     * Author Jason
     * Date 2020-03-29 21:44
     */
    @Override
    public void doHomework() {
        System.out.println("做Python课程作业.......");
    }
}
