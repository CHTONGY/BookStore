package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-19 7:53 上午
 */
public class JDBCUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> connections;

    static {
        try {
            connections = new ThreadLocal<>();
            Properties properties = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("com/atguigu/jdbc.properties");
            properties.load(is);

            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

//            System.out.println(dataSource.getConnection());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 小技巧：可以编写一个main函数使得这个类成为可执行类，使得这个类可以被加载到内存中，从而测试执行静态代码块中的语句
     */
//    public static void main(String[] args) {
//
//    }

    /**
     * 获取数据库连接池中的连接
     * @return 返回null则说明获取连接失败<br/>
     */
    public static Connection getConnection() {
        Connection connection = connections.get();
        if(connection == null) {
            try {
                connection = dataSource.getConnection();
                connections.set(connection);
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    /**
     * 提交事务并释放连接
     */
    public static void commitAndClose() {
        Connection connection = connections.get();
        if(connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        connections.remove();
    }

    /**
     * 回滚事务并释放连接
     */
    public static void rollbackAndClose(){
        Connection connection = connections.get();
        if(connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        connections.remove();
    }

//    /**
//     * 关闭连接，放回数据库连接池
//     * @param connection
//     */
//    public static void close(Connection connection) {
//        if(connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
