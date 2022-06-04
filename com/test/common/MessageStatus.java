package com.test.common;

/**
 * @Data: 2022-05-27
 * @Start Time: 20:51
 * 定义了表示不同消息类型的常量
 */


public interface MessageStatus {
    String MESSAGE_LOGIN_SUCCEED = "1";//登陆成功
    String MESSAGE_LOGIN_FAIL = "2";//登录失败
    String MESSAGE_COMM_MES = "3"; //普通信息包
    String MESSAGE_GET_ONLINE_FRIEND = "4"; //请求在线用户列表
    String MESSAGE_RET_ONLINE_FRIEND = "5"; //返回在线用户列表
    String MESSAGE_CLIENT_EXIT = "6"; //客户端请求退出
    String MESSAGE_TO_ALL_MES = "7"; //群发消息
}
