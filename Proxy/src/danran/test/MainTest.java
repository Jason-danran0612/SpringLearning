package danran.test;

import danran.proxy.*;

/**
 * @Classname MainTest
 * @Description TODO
 * @Date 2020/4/18 8:48
 * @Created by Jason
 */
public class MainTest {
    public static void main(String[] args) {
        try {
            School s = (School) new MyTestProxy().getInstance(new SchoolImpl());
            s.Learn();
//            控制台中文乱码解决方案
//            -Dfile.encoding=utf-8
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
