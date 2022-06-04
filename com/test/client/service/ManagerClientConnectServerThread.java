package com.test.client.service;

import com.test.client.view.MainView;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Data: 2022-05-27
 * @Start Time: 22:23
 * 管理客户端连接到服务器端的线程(ClientConnectServerThread)
 */


public class ManagerClientConnectServerThread {
    //key为用户id， value是线程
    private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();

    public static void addClientConnectServerThread(String userId, ClientConnectServerThread clientConnectServerThread){
        hm.put(userId, clientConnectServerThread);
    }
    //获取Id对应的线程们
    public static ClientConnectServerThread getClientConnectServerThread(String userId){
        return hm.get(userId);
    }


    //key为用户id， value是MainView
    private static HashMap<String, MainView> mainViewSet = new HashMap<>();

    public static void addMainView(String userId, MainView mainView){
        mainViewSet.put(userId, mainView);
    }
    public static MainView getMainView(String userId) {
        return mainViewSet.get(userId);
    }
    public static  HashMap<String, MainView> getMainViewSet() {
        return mainViewSet;
    }

}
