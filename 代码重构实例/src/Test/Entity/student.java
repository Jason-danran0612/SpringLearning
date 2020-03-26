package Test.Entity;

import java.io.Serializable;

/**
 * @Classname student
 * @Description TODO
 * @Date 2020/3/24 18:25
 * @Created by ASUS
 */
public class student implements Serializable {
    private int stu_Age;//The only number of a student in the database.
    private String stu_Name;//The name of the student.
    public student(){
    }

    public student(int stu_Age, String stu_Name) {
        this.stu_Age = stu_Age;
        this.stu_Name = stu_Name;
    }

    public int getStu_Age() {
        return stu_Age;
    }

    public void setStu_Age(int stu_Age) {
        this.stu_Age = stu_Age;
    }

    public String getStu_Name() {
        return stu_Name;
    }

    public void setStu_Name(String stu_Name) {
        this.stu_Name = stu_Name;
    }

    @Override
    public String toString() {
        return "student{" +
                "stu_Age=" + stu_Age +
                ", stu_Name='" + stu_Name + '\'' +
                '}';
    }
}
