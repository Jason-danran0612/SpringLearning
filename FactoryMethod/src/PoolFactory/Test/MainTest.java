package PoolFactory.Test;

import PoolFactory.JDBCPool.JDBCConnectionPool;
import PoolFactory.JDBCPool.Pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Classname MainTest
 * @Description TODO
 * @Date 2020/3/30 8:22
 * @Created by ASUS
 * 利用抽象工厂模式可以创建出不同类型的数据库连接池管理类（工厂类），可扩展性好
 * 同时在数据库连接池中有一定数量的连接可供使用，不必要再去重新创建新连接，放到
 * 容器中缓存，在业务需要的时候只需要现取现用，降低了业务调度的时间。
 */
public class MainTest {
    public static void main(String[] args) {
        Pool pool = JDBCConnectionPool.getInstance();//获取 JDBC 的数据库连接池实例
        System.out.println(pool.getNum());
        System.out.println(pool.getNumActive());
        Connection connection = pool.getConnection();
        Connection connection1 = pool.getConnection();
        Connection connection2 = pool.getConnection(10000L);
        System.out.println("空闲："+pool.getNum());
        System.out.println("正在使用："+pool.getNumActive());
        pool.freeConnection(connection1);
        System.out.println("空闲："+pool.getNum());
        String sql = "select * from t_student";
        ResultSet rs ;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
