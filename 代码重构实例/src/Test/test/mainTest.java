package Test.test;

import Test.Entity.student;
import Test.StudentService.stuService_2;

import java.util.List;

/**
 * @Classname mainTest
 * @Description TODO
 * @Date 2020/3/25 18:15
 * @Created by ASUS
 */
public class mainTest {
    public static void main(String[] args) {
        student stu = new student(32,"Owl City");
        stuService_2 service = new stuService_2();
        service.save_student(stu);
        student querystu = service.Query_stu(32);
        System.out.println(querystu);
        List<student> students = service.Query_stu();
        System.out.println(students);
    }
}
