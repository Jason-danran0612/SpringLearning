package Factory.Test;

import Factory.IEntity.ICourse;
import Factory.Entity.JavaCourse;
import Factory.IEntity.IExam;
import Factory.IFactory.ICourseFactory;
import Factory.IFactory.ICourseFactory_1;
import Factory.factory.*;


/**
 * @Classname MainTest
 * @Description TODO
 * @Date 2020/3/28 17:23
 * @Created by ASUS
 */
public class MainTest {
    public static void main(String[] args) {
        System.out.println("****简单工厂模式**");
        SimpleFactoryMode();
        System.out.println("****工厂模式*****");
        FactoryMode();
        System.out.println("****抽象工厂模式**");
        AbstractFactoryMode();
    }
    //抽象工厂模式
    //这部分代码很好地完成了两个产品族：Java课程、Python课程
    //如果要增加产品的等级，就需要将各个工厂的代码修改
    //则并符合开闭原则，这就是抽象工厂模式的缺点，但是对产品族的扩展性很好
    private static void AbstractFactoryMode() {
        ICourseFactory_1 factory_1 = new JavaFactory_1();
        ICourse javaCourse = factory_1.createCourse();
        javaCourse.Learn();
        IExam javaExam = (IExam) javaCourse;//一个扩展的示例，类型转换
        javaExam.takeExam();
        factory_1.createHomework().doHomework();
        factory_1.createVideo().makeVideo();
        factory_1 = new PythonFactory_1();
        factory_1.createCourse().Learn();
        factory_1.createHomework().doHomework();
        factory_1.createVideo().makeVideo();
    }

    //工厂模式
    private static void FactoryMode() {
        ICourseFactory javaFactory = new JavaFactory();
        ICourseFactory pythonFactory = new PythonFactory();
        javaFactory.createCourse().Learn();
        pythonFactory.createCourse().Learn();
    }

    //简单工厂模式
    private static void SimpleFactoryMode() {
        CourseFactory_1 courseFactory_1 = new CourseFactory_1();
        ICourse python = courseFactory_1.creatCourse("Python");
        python.Learn();
        ICourse java = courseFactory_1.createCourse(JavaCourse.class);
        java.Learn();
    }

}
