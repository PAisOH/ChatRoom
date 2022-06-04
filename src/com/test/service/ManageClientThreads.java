package com.test.service;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @Data: 2022-05-28;
 *
 * @Start Time: 14:36
 */


public class ManageClientThreads {
    private static HashMap<String, ServerConnectClientThread> hm = new HashMap<>();

    public static HashMap<String, ServerConnectClientThread> getHm() {
        return hm;
    }

    //添加线程对象到hm集合
    public static void addClientThread(String userID, ServerConnectClientThread serverConnectClientThread){
        hm.put(userID, serverConnectClientThread);
    }

    //根据userId返回ServerConnectClientThread线程
    public static ServerConnectClientThread getServerConnectThread(String userID){
        return hm.get(userID);
    }

    //从集合中移除某个线程对象
    public static void removeServerConnectClientThread(String userId) {
        hm.remove(userId);
    }

    //返回在线用户列表
    public static String getOnlineUser(){
        Iterator<String> iterator = hm.keySet().iterator();
        String onlineUserList = " ";
        while (iterator.hasNext()){
            onlineUserList += iterator.next().toString() + " ";
        }
        return onlineUserList;
    }
}
