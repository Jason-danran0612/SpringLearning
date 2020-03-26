package Test.StudentService;

import Test.Entity.student;
import Test.Utils.DbUtil;

import java.sql.*;

/**
 * @Classname stuService
 * @Description TODO
 * @Date 2020/3/24 18:30
 * @Created by ASUS
 */
public class stuService implements IService{
    /**
     *功能描述：将传入的学生信息加入到数据库中
     *@param stu the student need to be saved.
     *Author Jason
     *Date 2020-03-24 18:33
     */
    @Override
    public void save_student(student stu){
        String sql = "INSERT INTO t_student(name,age) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int age = stu.getStu_Age();
        String name = stu.getStu_Name();
        try {
            Class.forName("com.mysql.jdbc.Driver");//注册驱动
            connection = DriverManager.getConnection//获取连接
                    ("jdbc:mysql://localhost:3306/springmodel?serverTimezone=UTC&characterEncoding=utf8","root","");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,name);
            preparedStatement.setObject(2,age);
            preparedStatement.execute();//执行SQL语句
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closePmCon(preparedStatement, connection);
        }
    }



    /**
     *功能描述：根据id值在数据库中检索并删除数据
     *@param id the id of the student that need to be deleted.
     *Author Jason
     *Date 2020-03-24 18:36
     */
    @Override
    public void delete_stu_byId(int id){
        String sql = "DELETE FROM t_student WHERE age=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//注册驱动
            connection = DriverManager.getConnection//获取连接
                    ("jdbc:mysql://localhost:3306/springmodel?serverTimezone=UTC&characterEncoding=utf8","root","");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,id);
            preparedStatement.execute();//执行SQL语句

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.closePmCon(preparedStatement, connection);
        }

    }
    /**
     *功能描述：更新学生的信息数据
     *@param stu the student that need to be pull into database.
     *Author Jason
     *Date 2020-03-24 18:37
     */
    @Override
    public void update_stu(student stu){
        String sql = "UPDATE t_student SET name=? WHERE age=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//注册驱动
            connection = DriverManager.getConnection//获取连接
                    ("jdbc:mysql://localhost:3306/springmodel?serverTimezone=UTC&characterEncoding=utf8","root","");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,stu.getStu_Name());
            preparedStatement.setObject(2,stu.getStu_Age());
            preparedStatement.execute();//执行SQL语句

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.closePmCon(preparedStatement, connection);
        }
    }


}
