package Test.StudentService;

import Test.Entity.student;
import Test.Utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname stuService_1
 * @Description TODO
 * @Date 2020/3/25 18:14
 * @Created by ASUS
 * 使用工具类：DbUtil进行代码的重构，抽取之前重复的代码，提高代码的复用率
 * 同时使用了IService接口可以方便功能的扩展
 */
public class stuService_1 implements IService{

    @Override
    public void save_student(student stu) {
        String sql = "INSERT INTO t_student(name,age) VALUES (?,?)";
        execute_stu(stu, sql);
    }

    @Override
    public void delete_stu_byId(int age) {
        String sql = "DELETE FROM t_student WHERE age=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbUtil.getConnect();//获取连接
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,age);
            preparedStatement.execute();//执行SQL语句
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(null, preparedStatement,connection);//工具类的静态关闭连接方法
        }
    }

    @Override
    public void update_stu(student stu) {
        String sql = "UPDATE t_student SET name=? WHERE age=?";
        execute_stu(stu, sql);
    }
    //对学生做出跟传入的sql语句匹配的操作
    private void execute_stu(student stu, String sql) {
        Connection connection = DbUtil.getConnect();//获取连接
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,stu.getStu_Name());
            preparedStatement.setObject(2,stu.getStu_Age());
            preparedStatement.execute();//执行SQL语句

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(null, preparedStatement,connection);//工具类的静态关闭连接方法
        }
    }
    //根据年龄（当前认为的主键）查询
    public student Query_stu(int age){
        student stu = null;
        String sql = "SELECT * FROM t_student WHERE age = ?"; //目前认为age为主键
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = DbUtil.getConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,age);
            rs = preparedStatement.executeQuery();
            if (rs.next()){
                stu = new student(rs.getInt("age"),rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {//释放资源
            DbUtil.close(rs,preparedStatement,connection);
            return stu;
        }
    }
    //返回所有表中的数据
    public List<student> Query_stu(){
        List<student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM t_student";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = DbUtil.getConnect();
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();//查询数据
            while (rs.next()){
                student stu  = new student(rs.getInt("age"),rs.getString("name"));
                studentList.add(stu);//添加数据到集合中
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {//释放资源
            DbUtil.close(rs,preparedStatement,connection);
            return studentList;
        }
    }
}
