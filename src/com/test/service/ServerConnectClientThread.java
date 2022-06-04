package com.test.service;

import com.test.common.Message;
import com.test.common.MessageStatus;
import com.test.db.DBUserAccount;
import com.test.view.ServerUI;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @Data: 2022-05-28
 * @Start Time: 14:09
 * Server的通信线程: 该类的一个对象和某个客户端保持通讯
 */


public class ServerConnectClientThread extends Thread{
    private Socket socket;
    private String userId; //连接到服务端的用户ID
//    private DBUserAccount dbUserAccount;
    ServerUI serverUI = null;

    public ServerConnectClientThread(Socket socket, String userId, ServerUI serverUI) {
        this.socket = socket;
        this.userId = userId;
//        dbUserAccount = new DBUserAccount();
        this.serverUI = serverUI;
    }

    @Override
    //发送接收客户端消息
    public void run() {
        while (true){
            try {
                System.out.println("服务器和客户端 "+ userId +" 保持通信，读取数据...");
                serverUI.getTa_diary().append("\n 服务器和客户端 "+ userId +" 保持通信，读取数据...");
                DBUserAccount.updateToSQL(userId, 1);
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) ois.readObject(); //一直等待读写数据

                //根据msg的类型，做相应的业务处理
                //客户端请求在线用户列表
                if(MessageStatus.MESSAGE_GET_ONLINE_FRIEND.equals(msg.getMesStatus())){
                    System.out.println(msg.getSender() + "请求在线用户列表");
                    serverUI.getTa_diary().append("\n" + msg.getSender() + "请求在线用户列表");
                    String onlineUser = ManageClientThreads.getOnlineUser();
                    //构建一个msg返回给客户端
                    Message msg2 = new Message();
                    msg2.setMesStatus(MessageStatus.MESSAGE_RET_ONLINE_FRIEND);
                    msg2.setContent(onlineUser);
                    msg2.setGetter(msg.getSender());
                    //返回给客户端
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(msg2);
                } else if (MessageStatus.MESSAGE_COMM_MES.equals(msg.getMesStatus())) {
                    //msg获取getter
                    ServerConnectClientThread serverConnectThread = ManageClientThreads.getServerConnectThread(msg.getGetter());
                    ObjectOutputStream oos = new ObjectOutputStream(serverConnectThread.getSocket().getOutputStream());
                    oos.writeObject(msg); //TODO: 离线留言-用户不在线，可以保存到数据库
                    serverUI.getTa_diary().append("\n"  + msg.getSendTime() + msg.getSender() + " 对 " + msg.getGetter() + "说: " + msg.getContent());
                } else if(MessageStatus.MESSAGE_CLIENT_EXIT.equals(msg.getMesStatus())) {
                    System.out.println(msg.getSender() + "退出");
                    //修改数据库在线的状态
                    DBUserAccount.updateToSQL(msg.getSender(), 0);
                    serverUI.getTa_diary().append("\n" + msg.getSender() + "退出");
                    serverUI.updataList();
                    //将客户端对应线程从集合中删除
                    ManageClientThreads.removeServerConnectClientThread(msg.getSender());
                    socket.close();//关闭连接
                    break;//退出run() => 线程结束
                } else if(MessageStatus.MESSAGE_TO_ALL_MES.equals(msg.getMesStatus())) {
                    //遍历管理线程的集合，得到所有线程的socket，实现群发
                    HashMap<String, ServerConnectClientThread> hm = ManageClientThreads.getHm();
                    Iterator<String> iterator = hm.keySet().iterator();
                    while (iterator.hasNext()) {
                        //取出在线用户的Id
                        String onlineUserId = iterator.next().toString();
                        //排除发送者自己
                        if(!onlineUserId.equals(msg.getSender())) {
                            //得到对象输出流，进行转发msg
                            ObjectOutputStream oos =
                                    new ObjectOutputStream(hm.get(onlineUserId).getSocket().getOutputStream());
                            oos.writeObject(msg);
                        }
                    }
                }
                else {
                    System.out.println("其他类型");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public Socket getSocket() {
        return socket;
    }
}
