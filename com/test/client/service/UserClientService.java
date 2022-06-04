package com.test.client.service;

import com.test.client.view.MainView;
import com.test.common.Message;
import com.test.common.MessageStatus;
import com.test.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @Data: 2022-05-27
 * @Start Time: 21:43
 * 客户端接收服务器端数据，在此检验是否登入信息
 */


public class UserClientService {
    private User u = new User();//User,Socket 做成属性，因为其他地方也可能使用，通过getter、setter即可调用
    private Socket socket;
    private MainView mainView;

    public boolean checkUser(String userID, String pwd){
        boolean isLoginSucceed = false;
        //创建User对象
        u.setUserId(userID);
        u.setPasswd(pwd);

        try {
            //连接服务端，发送u对象
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);//代表本地ip地址, 监听端口 TODO: 之后设定为用户定义的端口号
            //获取ObjectOutputStream对象输出流的对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u); //发送User对象到数据通道to服务器

            //读取服务器回复的Message对象(服务器验证账户信息)
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message)ois.readObject(); //向下转型，且抛出异常


            if(MessageStatus.MESSAGE_LOGIN_SUCCEED.equals(ms.getMesStatus())){//登陆成功OK
                //创建一个线程，持有一个socket，保持和服务器端一直通讯 -> 创建一个线程类 ClientConnectServerThread
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                //启动客户端线程
                //TODO：最好也把start()这句放入集合类中
                clientConnectServerThread.start();
                //为了后面客户端的扩展，将客户端线程放入集合(HashMap)中管理
                ManagerClientConnectServerThread.addClientConnectServerThread(userID, clientConnectServerThread);
                isLoginSucceed = true;
            } else{ //登陆失败OK，不能启动和服务器通信的线程 => 关闭socket
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isLoginSucceed;
    }

    //向服务器端请求在线用户列表
    public void onlineFriendList(){
        //发送一个msg, MESSAGE_GET_ONLINE_FRIEND类型
        Message message = new Message();
        message.setMesStatus(MessageStatus.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(u.getUserId());

        //发送给服务器
        try {
            //从管理线程的集合中，通过userId获取该线程对象
            ClientConnectServerThread clientConnectServerThread =
                    ManagerClientConnectServerThread.getClientConnectServerThread(u.getUserId());
            //得到userId对应的线程对象
            Socket socket = clientConnectServerThread.getSocket();

            //得到当前线程的Socket对应的ObjectOutputStream对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 退出客户端，并给服务端发送一个退出体统的message
     */
    public void logOut() {
        Message message = new Message();
        message.setMesStatus(MessageStatus.MESSAGE_CLIENT_EXIT);
        message.setSender(u.getUserId()); //指定是哪一个客户端id来关闭线程

        //发送message
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            System.out.println(message.getSender() + " 退出了系统");
            System.exit(0); //结束进程
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 桥接
     * @param mainView
     */
    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
