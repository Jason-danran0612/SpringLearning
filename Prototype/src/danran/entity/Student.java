package danran.entity;

import danran.Iprototype.Prototype;

import java.io.Serializable;
import java.util.Map;

/**
 * @Classname Student
 * @Description TODO
 * @Date 2020/4/17 11:26
 * @Created by ASUS
 */
public class Student implements Prototype, Serializable {
    private int stuId;
    private String stuName;
//    学生的成绩单
    private Map<String,Integer> stuScore;
    public Student(){
    }

    public Student(int stuId, String stuName, Map<String, Integer> stuScore) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuScore = stuScore;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Map<String, Integer> getStuScore() {
        return stuScore;
    }

    public void setStuScore(Map<String, Integer> stuScore) {
        this.stuScore = stuScore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuScore=" + stuScore +
                '}';
    }

    @Override
//    浅克隆方式
    public Student clone() {
        Student student = new Student();
        student.setStuId(this.stuId);
        student.setStuName(this.stuName);
        student.setStuScore(this.stuScore);
        return student;
    }
}
