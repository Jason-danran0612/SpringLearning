package PoolFactory.JDBCPool;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Classname Pool
 * @Description TODO
 * @Date 2020/3/30 8:23
 * @Created by ASUS
 * 数据库连接池的工厂模式方法-->抽象的工厂
 * 自定义连接池<code>getInstance()</code>,返回唯一的Pool实例，第一次调用时执行构造函数
 * 构造函数<code>Pool()</code>调用装载驱动函数<code>LoadDriver()</code>；<code>createPool()</code>函数创建数据库连接池
 * <code>getConnection()</code>返回连接,<code>freeConnection()</code>释放连接到连接池中
 * <code>getNum()</code>返回连接池中的空闲数据库连接数
 * <code>getNumActive()</code>返回正在使用的数据库连接数
 */
public abstract class Pool {
    //配置文件地址，可以通过修改配置文件的信息实现相应的更改
    public String propertiesFileName = "config/mysql.properties";
    private static Pool instance = null;//定义唯一的实例
    protected int maxConnect = 0;//最大连接数
    protected int normalConnect = 0;//空闲连接数
    protected String driverName = null;//驱动字符串
    protected Driver driver = null;//驱动变量
    //私有的构造函数
    protected Pool(){
        try {
            init();
            LoadDrivers(driverName);
        } catch (IOException e) {
            System.out.println("发生错误："+e.toString()+"在Pool.java");
        }
    }
    //读取配置文件中的成员变量
    private void init() throws IOException {
        InputStream is = new FileInputStream(new File(propertiesFileName));
        Properties properties = new Properties();
        properties.load(is);//加载配置文件信息
        this.driverName = properties.getProperty("driver");
        this.maxConnect = Integer.parseInt(properties.getProperty("maxConnect"));
        this.normalConnect = Integer.parseInt(properties.getProperty("normalConnect"));
    }
    //加载数据库驱动
    private void LoadDrivers(String driverName) {
        try {
            driver = (Driver) Class.forName(driverName).getConstructor().newInstance();
            DriverManager.registerDriver(driver);
            System.out.println("成功注册数据库驱动.....");
        } catch (Exception e) {
            System.out.println("数据库驱动加载失败，因为：" + e.toString());
        }
    }
    //创建连接池
    public abstract void createPool();
    /**
     *功能描述：（单例模式）返回数据库连接池的实例 <code>Pool</code>
     *Author Jason
     *Date 2020-03-30 12:07
     */
    public static synchronized Pool getInstance() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if(instance != null) {
            instance = (Pool) Class.forName("PoolFactory.JDBCPool.Pool").getDeclaredConstructor().newInstance();
        }
        return instance;
    }
    //获得一个可用的连接，且小于最大连接数
    public abstract Connection getConnection();
    //获得连接，有时间限制
    public abstract Connection getConnection(long time);
    //释放连接
    public abstract void freeConnection(Connection connection);
    //获取可用连接
    public abstract int getNum();
    //获得正在使用连接数
    public abstract int getNumActive();
    //撤销驱动
    protected synchronized void release(){
        try {
            DriverManager.deregisterDriver(driver);
            System.out.println("撤销JDBC驱动" + driver.getClass().getName());
        } catch (SQLException e) {
            System.out.println("午饭撤销JDBC驱动" + driver.getClass().getName() + "并发生错误：" + e.toString());
        }
    }
}
