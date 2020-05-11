package danran.test;
import danran.entity.CloneTool;

/**
 * @Classname MinTest
 * @Description TODO
 * @Date 2020/4/17 11:45
 * @Created by ASUS
 */
public class MinTest {
    public static void main(String[] args) {
//        浅度克隆
        CloneTool.shallowClone();
//        深度克隆
        CloneTool.deepClone();
    }
}
