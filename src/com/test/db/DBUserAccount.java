package com.test.db;

import com.test.common.User;
import com.test.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Data: 2022-06-03
 * @Start Time: 13:18
 */


public class DBUserAccount {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    //创建一个集合：存放多个用户，若这些用户登录，就认为是合法的
    //TODO: 用么用SQL 要么换成ConcurrentHashMap-可以处理并发的集合，线程安全(线程同步)
    public static ConcurrentHashMap<String, User> validUsers = new ConcurrentHashMap<>();
//    //静态代码块初始化validUsers
//    static {
//        validUsers.put("100", new User("100", "123456"));
//        validUsers.put("200", new User("200", "123456"));
//        validUsers.put("300", new User("300", "123456"));
//        validUsers.put("小倩", new User("小倩", "123456"));
//        validUsers.put("姥姥", new User("姥姥", "123456"));
//    }

    public DBUserAccount(){
        addUserFromDB();
    }

//    public static DBUserAccount dbUserAccount = new DBUserAccount();
    //验证用户是否有效的方法
    public boolean checkUser(String userId, String passwd){
        User user = validUsers.get(userId);
        //userId没存在在validUsers的key中
        if(user == null){
            System.out.println("userId" + userId + "用户不存在");
            return false;
        }
        //密码输入错误
        if(!passwd.equals(user.getPasswd())){
            return false;
        }
        //成功
        return true;
    }

    /**
     * 启动服务器是，拿到DataBase里的user_account表,存入validUsers集合
     * SQL语句爆红，但正常执行
     */
    private void addUserFromDB() {
        try {
            int count = 0;
            conn = DBUtil.getConnection();
            if(conn == null) System.out.println("数据库连接失败");
            sql = " select count(*) from user_account ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                count = rs.getInt(1);
            }
            sql = " select * from user_account ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                String id = rs.getString(1);
                int isOnline = rs.getInt(3);
                User user = new User(id, rs.getString(2), isOnline);
                validUsers.put(id, user);
            }
            System.out.println("!!!" + validUsers.get("小张").getUserId() + " " + validUsers.get("小张").getPasswd() + " " + validUsers.get("小张").getIsOnline());
//            DBUtil.closeResource(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ConcurrentHashMap<String, User> getValidUsers() {
        return validUsers;
    }

    /**
     * 设置集合里某用户在线状态，并传到SQL
     */
    public void setOnlineToSQL(ConcurrentHashMap<String, User> validUsers) {
        validUsers.get(validUsers).setIsOnline(1);
        sql = "  ";
    }

    /**
     * 设置集合里某用户下线状态，并传到SQL
     */
    public void setOfflineToSQL () {

    }

    /**
     * 更改集合里某用户状态，并传到SQL
     */
    public void updateToSQL (User user) {

    }

    public void updateToSQL (String userId, String password, int isOnline) {

    }

    /**
     * 主要用于用户上线、下线
     * @param isOnline 在线状态
     */
    public static void updateToSQL(String userId, int isOnline) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = null;
        validUsers.get(userId).setIsOnline(isOnline);
        String passwd = validUsers.get(userId).getPasswd();
        try {
            conn = DBUtil.getConnection();
            if(conn == null) System.out.println("数据库连接失败");
            sql = " update user_account set isonline = ? where userid = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, isOnline);
            ps.setString(2, userId);
            ps.executeUpdate(); // !!! 别忘写了
            System.out.println(isOnline);
            System.out.println(userId);
            System.out.println("!!!状态修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
