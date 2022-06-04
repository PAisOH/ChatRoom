package com.test.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//连接mysql工具类
public class DBUtil {
    public static String DRIVERNAME = null;
    public static String URL = null;
    public static String USER = null;
    public static String PASSWORD = null;

    public static Connection conn = null;

    static {
        try {
            Properties props = new Properties();
            InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            props.load(in);

            DRIVERNAME = props.getProperty("driver");
            URL = props.getProperty("url");
            USER = props.getProperty("username");
            PASSWORD = props.getProperty("password");
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    //获取连接
    public static Connection getConnection() throws Exception{
        if (conn != null){
            return conn;
        }
        //注册驱动
        Class.forName(DRIVERNAME);
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }

    //关闭连接
    public static void closeResource(Connection conn, PreparedStatement ps) throws SQLException {
        ps.close();
        conn.close();
    }

    public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException{
        ps.close();
        rs.close();
        conn.close();
    }
}