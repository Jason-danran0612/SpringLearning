package danran.entity;

import danran.Iprototype.Prototype;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname CloneTool
 * @Description TODO
 * @Date 2020/4/17 11:42
 * @Created by ASUS
 */
public class CloneTool {
    public static Prototype getClone(Prototype prototype){
        return prototype.clone();
    }
//    深度克隆测试方法
    public static void deepClone() {
        Map<String,Integer> score = new HashMap<>();
        score.put("English",90);
        score.put("Math",97);
        score.put("Java",99);
        Student student = new Student(1,"Jason",score);
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(student);
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            Student student1 = (Student) ois.readObject();
            System.out.println("两个学生是否一致？：" + (student == student1));
            System.out.println("更改student的成绩单，添加C语言成绩");
            student.getStuScore().put("C",85);
            System.out.println("student:" + student);
            System.out.println("studentClone:" + student1);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    浅克隆测试方法
    public static void shallowClone() {
        Map<String,Integer> score = new HashMap<>();
        score.put("English",90);
        score.put("Math",97);
        score.put("Java",99);
        Student student = new Student(1,"Jason",score);
        Student studentClone = (Student) CloneTool.getClone(student);
        studentClone.setStuName("JasonClone");
        System.out.println("对象地址值是否一致？：" + (student == studentClone));
        System.out.println("Map地址值是否一致？：" + (student.getStuScore() == studentClone.getStuScore()));
        System.out.println("student:Jason:" + student.toString());
        System.out.println("student:JasonClone:" + student.toString());
        System.out.println("向student中添加C语言成绩95");
        student.getStuScore().put("C",95);
        System.out.println("studentClone的成绩单：");
        System.out.println(studentClone.getStuScore());
        student.setStuName("sdg");
        System.out.println(student.getStuName());
        System.out.println(studentClone.getStuName());
    }
}
