package com.test.client.service;

import com.test.client.view.MainView;
import com.test.common.Message;
import com.test.common.MessageStatus;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @Data: 2022-05-27
 * @Start Time: 22:05
 * 客户端通信线程
 *  UI:接收服务端信息，显示消息
 */


public class ClientConnectServerThread extends Thread{
    //必须持有Socket，保持通讯
    private Socket socket;
    private  MainView mainView;

    public ClientConnectServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        //Thread需要保持在后台和服务器通信，故做成永真循环控制
        while (true){
            try {
                System.out.println("客户端线程，等待读取从服务器端发来的消息");

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message)ois.readObject(); //readObject的异常，如果服务器没有数据发过来，阻塞在这里一直等待
                //TODO: 被动刷新
                //判断msg类型，做相应业务处理
                if(MessageStatus.MESSAGE_RET_ONLINE_FRIEND.equals(msg.getMesStatus())){
                    //取出在线用户列表信息
                    //规定
                    String[] onlineUsers = msg.getContent().split(" ");
                    System.out.println("\n=======当前在线用户列表======");
//                    mainView.getTa_list().setText("");
                    for(int i = 1; i < onlineUsers.length; i++){
                        System.out.println("用户: " + onlineUsers[i]);
                        mainView.getTa_list().append(onlineUsers[i] + "\n");
                    }
//                    mainView.getTa_list().append(msg + "\n");
                } else if (MessageStatus.MESSAGE_COMM_MES.equals(msg.getMesStatus())) {
                    //从服务器转发的消息，显示到控制台即可
                    System.out.println("\n" + msg.getSender() + " 对 "+ msg.getGetter() + "说: " + msg.getContent());
                    mainView = ManagerClientConnectServerThread.getMainView(msg.getGetter());
                    mainView.getTa_output().append(msg.getSender() + " 对 " + msg.getGetter() + "(我)说: " + msg.getContent() + "\n");
                    //MainView不好穿过来，换个方向

                } else if (MessageStatus.MESSAGE_TO_ALL_MES.equals(msg.getMesStatus())) {
                    //显示在客户端界面
                    System.out.println("\n" + msg.getSender() + " 对 大家说: " + msg.getContent());
                    HashMap<String, MainView> mainViewSet = ManagerClientConnectServerThread.getMainViewSet();
                    for(Map.Entry entry: mainViewSet.entrySet()){
                        mainView = ManagerClientConnectServerThread.getMainView(entry.getKey().toString());
                        mainView.getTa_output().append(msg.getSender() + " 对 大家说: " + msg.getContent() + "\n");
                    }
                } else {
                    System.out.println("其他类型msg，暂不处理");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getSocket() { //未来还可能用到Socket
        return socket;
    }

    public MainView getMainView() {
        return mainView;
    }
}
