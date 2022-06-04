package com.test.common;

import java.io.Serializable;

/**
 * @Data: 2022-05-27
 * @Start Time: 20:37
 * 一位的用户信息
 */


public class User implements Serializable {
    private static final long serialVersionUID = 1L; //增强序列化兼容性
    private String userId;
    private String passwd;
    private int isOnline;

    public User(String userId, String passwd, int isOnline) {
        this.userId = userId;
        this.passwd = passwd;
        this.isOnline = isOnline;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }
}
