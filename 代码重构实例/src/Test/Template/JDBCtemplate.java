package Test.Template;

import Test.StudentService.IRowMapper;
import Test.Utils.DbUtil;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Classname JDBCtemplate
 * @Description TODO
 * @Date 2020/3/26 9:38
 * @Created by ASUS
 */
public class JDBCtemplate {
    //Query template统一查询模版(DQL),借助泛型方便功能的扩展
    @Nullable
    public static <E> E Query(String sql, IRowMapper<E> mapper, Object...params){
//        List<student> studentList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = DbUtil.getConnect();
            preparedStatement = connection.prepareStatement(sql);
            setPreparedStatement(preparedStatement,params);
            rs = preparedStatement.executeQuery();
            return mapper.mapping(rs);//处理结果集，由实现类自己完成
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(rs,preparedStatement,connection);
        }
        return null;
    }
    //DML
    public static void Manipulation(String sql,Object...params){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbUtil.getConnect();
            preparedStatement = connection.prepareStatement(sql);
            setPreparedStatement(preparedStatement,params);//设置值
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {//释放资源
            DbUtil.close(null,preparedStatement,connection);
        }
    }
    //处理preparedStatement,设置值
    private static void setPreparedStatement(PreparedStatement preparedStatement,Object... params) throws SQLException {
        for(int i = 0; i < params.length;i++){
            preparedStatement.setObject(i+1,params[i]);
        }
    }
}
