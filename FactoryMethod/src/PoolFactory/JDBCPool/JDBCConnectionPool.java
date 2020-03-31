package PoolFactory.JDBCPool;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 * @Classname DBConnectionPool 数据库连接池管理类
 * @Description TODO
 * @Date 2020/3/30 9:23
 * @Created by ASUS
 * 实例化的数据库连接池的工厂，产生JDBC数据库连接池的实例，不允许被继承
 * 是抽象工厂模式的具象化，同样可以Pool创建新的如ODBC连接池的实例，只需要修改配置文件即可
 */
public final class JDBCConnectionPool extends Pool {
    private int checkedOut;
    private List<Connection> freeConnections = new ArrayList<>();//存放连接的容器
    private String password = null;
    private String url = null;
    private String userName = null;
    private static int Num = 0;
    private static int ActiveNum = 0;
    private static JDBCConnectionPool pool = null;
    //产生数据库连接池
    public static synchronized JDBCConnectionPool getInstance(){
        if(pool == null){
            pool = new JDBCConnectionPool();
        }
        return pool;
    }
    //构造函数
    private JDBCConnectionPool(){
        try {
            init();
            for (int i = 0;i < normalConnect;i++){
                Connection connection = newConnection();
                if(connection != null){
                    freeConnections.add(connection);
                    Num++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //创建新连接
    private Connection newConnection() {
        Connection connection;
        try {
            if(userName == null){
                connection = DriverManager.getConnection(url);
            }else {
                connection = DriverManager.getConnection(url,userName,password);
            }
            System.out.println("创建一个新的连接...");
        } catch (SQLException e) {
            System.out.println("不能创建连接" + e.toString());
            return null;
        }
        return connection;
    }
    //初始化
    private void init() throws IOException {
        InputStream is = new FileInputStream(new File(propertiesFileName));
        Properties p = new Properties();
        p.load(is);
        this.userName = p.getProperty("user");
        this.password = p.getProperty("password");
        this.driverName = p.getProperty("driver");
        this.url = p.getProperty("url");
        this.maxConnect = Integer.parseInt(p.getProperty("maxConnect"));
        this.normalConnect = Integer.parseInt(p.getProperty("normalConnect"));
    }
    @Override
    public void createPool() {
        pool = new JDBCConnectionPool();
        System.out.println("数据库连接池创建成功...");
    }

    @Override
    //单例模式，获取一个可用连接
    public synchronized Connection getConnection(){
        Connection connection = null;
        if(freeConnections.size() > 0){//连接池中还有空闲的连接
            Num--;
            connection = freeConnections.get(0);//获取连接
            freeConnections.remove(0);//移除掉使用的连接
            try {
                if(connection.isClosed()){//连接关闭后，失去意义，从数据库连接池中重新获取连接
                    System.out.println("删除一个无用连接...");
                    connection = getConnection();//重新获取
                }
            } catch (SQLException e) {
                System.out.println("发生错误，删除一个无用连接...");
                connection = getConnection();
                e.printStackTrace();
            }
        }else if(maxConnect == 0||checkedOut < maxConnect){
            connection = newConnection();
        }
        ActiveNum++;
        return connection;
    }

    @Override
    public synchronized Connection getConnection(long time) {
        long startTime = new Date().getTime();//获取当前时间
        Connection connection;
        while ( (connection = getConnection()) == null){
            try {
                System.out.println("线程等待");
                wait(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if( (new Date().getTime() - startTime) > time){//等待时间超过给定时间，退出
                return null;
            }
        }
        ActiveNum++;
        return connection;
    }

    @Override
    //使用该方法将连接释放，回到连接池中
    public synchronized void freeConnection(Connection connection) {
        freeConnections.add(connection);
        Num++;
        checkedOut--;
        ActiveNum--;
        notifyAll();//解锁
    }

    @Override
    public int getNum() {
        return Num;
    }

    @Override
    public int getNumActive() {
        return ActiveNum;
    }
    //关闭连接
    public synchronized void release(){
        try {
            Enumeration<Connection> allConnections = Collections.enumeration(freeConnections);
            while (allConnections.hasMoreElements()){
                Connection connection = allConnections.nextElement();
                try {
                    connection.close();
                    Num--;
                } catch (SQLException e) {
                    System.out.println("无法关闭连接...");
                    e.printStackTrace();
                }
            }
            freeConnections.clear();
            ActiveNum = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            super.release();
        }
    }
    
}
