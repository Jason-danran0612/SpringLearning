package Test.Utils;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * @Classname DbUtil
 * @Description TODO
 * @Date 2020/3/25 18:22
 * @Created by ASUS
 */
public class DbUtil implements Serializable {
    private static Properties properties = null;
    private static Properties properties_SQL = null;

    static {//静态的代码块，一旦产生该类的对象，自动执行该代码块
        /*使用static 静态初始化代码块时候注意应用
         *try{....}catch(Exception e){...},
         *否则会发生类初始化异常*/
        try{
//            ClassLoader loader = Thread.currentThread().getContextClassLoader();
//            InputStream is = loader.getResourceAsStream("");
            InputStream is = new FileInputStream(new File("config/mysql.properties"));
            InputStream is_sql = new FileInputStream(new File("config/SQL.properties"));
            properties = new Properties();
            properties_SQL = new Properties();
            properties.load(is);        //加载数据库的properties文件
            properties_SQL.load(is_sql);//加载SQL语句的properties文件
            //加载数据库注册驱动
            Class.forName(properties.getProperty("driver"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *功能描述：返回数据库的连接connection
     * 声明为类的静态方法
     */
    public static Connection getConnect(){
        Connection connection = null;
        String url = properties.getProperty("url");
        String name = properties.getProperty("user");
        String pwd = properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url,name,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  connection;
    }
    /**
     *功能描述：关闭数据库相关的连接
     *Author Jason
     *Date 2020-03-26 8:06
     */
    public static void close(ResultSet resultSet,PreparedStatement preparedStatement,Connection connection) {
        try{
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closePmCon(preparedStatement, connection);
        }
    }
    public static void closePmCon(PreparedStatement preparedStatement, Connection connection) {
        try{
            if(preparedStatement != null){
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * @param SqlType 想要获取的SQL语句字符串
     * @return string <P>返回在配置文件中以<code>SqlType</code>对应的SQL语句字符串</P>
     */
    public static String getSQL(String SqlType){
        return properties_SQL.getProperty(SqlType);
    }
}
