//package com.test.service;
//
//import com.test.common.MessageStatus;
//import com.test.common.Message;
//import com.test.common.User;
//import com.test.db.DBUserAccount;
//
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//
///**
// * @Data: 2022-05-28
// * @Start Time: 13:40
// * 服务器，在监听端口9999，等待客户端的连接，并保持通讯
// */
//
//
//public class ChatServer {
//    private ServerSocket ss = null;
//    private DBUserAccount dbUserAccount = null;
//
//    public ChatServer() {
//        dbUserAccount = new DBUserAccount();
////        ConcurrentHashMap<String, User> validUsers = DBUsers.getValidUsers();
//
//        try {
//            //TODO: 端口写在配置文件
//            ss = new ServerSocket(9999);
//            System.out.println("服务端在9999端口监听...");
//            //和某个客户端连接后，会继续监听，所以永真循环
//            while (true) {
//                Socket socket = ss.accept(); //若没有客户端连接，就阻塞在这里
//                //socket关联的对象输入流
//                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//                //socket关联的对象输出流
//                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//
//                User u = (User)ois.readObject();//读取客户端发来的User对象
//                //创建个Message对象回复客户端
//                Message message = new Message();
//
//                // TODO: DB数据库验证
//                if(dbUserAccount.checkUser(u.getUserId(), u.getPasswd())){ //登录成功
//                    message.setMesStatus(MessageStatus.MESSAGE_LOGIN_SUCCEED);
//                    //将msg回复给客户端
//                    oos.writeObject(message);
//                    //创建一个和客户端保持通信的 持有socket对象的 线程
//                    ServerConnectClientThread serverConnectClientThread
//                            = new ServerConnectClientThread(socket, u.getUserId());
//                    //启动线程
//                    serverConnectClientThread.start();
//                    //该线程放入一个集合中进行管理
//                    ManageClientThreads.addClientThread(u.getUserId(), serverConnectClientThread);
//                } else { //登录失败
//                    message.setMesStatus(MessageStatus.MESSAGE_LOGIN_FAIL);
//                    oos.writeObject(message);
//                    //关闭socket
//                    socket.close();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                //若服务器退出了while，说明服务端不再监听=>关闭ServerSocket
//                ss.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//
//}
