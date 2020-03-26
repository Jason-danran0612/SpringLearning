package Test.StudentService;

import Test.Entity.student;

/**
 * @Classname IService
 * @Description TODO
 * @Date 2020/3/25 18:14
 * @Created by ASUS
 */
public interface IService {
    void save_student(student stu);
    void delete_stu_byId(int id);
    void update_stu(student stu);
}
