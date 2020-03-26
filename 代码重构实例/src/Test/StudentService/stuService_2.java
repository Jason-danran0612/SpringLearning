package Test.StudentService;

import Test.Entity.student;
import Test.Template.JDBCtemplate;
import Test.Utils.DbUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname stuServicee_2
 * @Description TODO
 * @Date 2020/3/26 9:35
 * @Created by ASUS
 */
public class stuService_2 implements IService {
    //各种在SQL语句存储在config/SQL.properties文件中
    private static final String INSERT = "INSERT";
    private static final String UPDATE = "UPDATE";
    private static final String DELETE = "DELETE";
    private static final String QUERY_AGE = "QUERY_AGE";
    private static final String QUERY_ALL = "QUERY_ALL";
    @Override
    public void save_student(student stu) {
        String sql = DbUtil.getSQL(INSERT);
        JDBCtemplate.Manipulation(sql,stu.getStu_Name(),stu.getStu_Age());
    }

    @Override
    public void delete_stu_byId(int id) {
        String sql = DbUtil.getSQL(DELETE);
        JDBCtemplate.Manipulation(sql,id);
    }

    @Override
    public void update_stu(student stu) {
        String sql = DbUtil.getSQL(UPDATE);
        JDBCtemplate.Manipulation(sql,stu.getStu_Name(),stu.getStu_Age());
    }
    //根据年龄（当前认为的主键,实际数据库中没有设置主键）查询
    public student Query_stu(int age){
        String sql = DbUtil.getSQL(QUERY_AGE);
        List<student> students = JDBCtemplate.Query(sql, new StudentRowMapper(),age);
        assert students != null;
        return students.size() > 0 ? students.get(0) : null;
    }
    //以List集合的形式，返回所有表中的数据
    public List<student> Query_stu(){
        String sql = DbUtil.getSQL(QUERY_ALL);
        return JDBCtemplate.Query(sql, new StudentRowMapper());
    }
    /* 到目前代码由最初较为臃肿的模块拆成小模块，各个模
     * 块之间耦合度降低，基本的功能都已经实现
     * 下面进行进一步的优化*/

    static class StudentRowMapper implements IRowMapper<List<student>>{
        /**
         * 功能描述：处理学生结果集数据
         * @param resultSet 待处理的结果集
         * Author Jason
         */
        @Override
        public List<student> mapping(ResultSet resultSet) throws Exception {
            List<student> students = new ArrayList<>();
            while (resultSet.next()){
                students.add(new student(resultSet.getInt("age"),resultSet.getString("name")));
            }
            return students;
        }
    }

}
