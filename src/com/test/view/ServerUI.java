/*
 * Created by JFormDesigner on Fri Jun 03 15:06:15 CST 2022
 */

package com.test.view;

import java.awt.*;
import com.test.common.Message;
import com.test.common.MessageStatus;
import com.test.common.User;
import com.test.db.DBUserAccount;
import com.test.service.ManageClientThreads;
import com.test.service.ServerConnectClientThread;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * @author Autumn
 */
public class ServerUI extends JFrame {
    private ServerSocket ss = null;
    private DBUserAccount dbUserAccount = null;
    private ServerUIHandler serverUIHandler;

    public ServerUI() {
        initComponents();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        dbUserAccount = new DBUserAccount();
        serverUIHandler = new ServerUIHandler(this);
        btn_exit.addActionListener(serverUIHandler);

        try {
            //TODO: 端口写在配置文件
            ss = new ServerSocket(9999);
            System.out.println("服务端在9999端口监听...");
            ta_diary.append("服务端在9999端口监听...");
            //和某个客户端连接后，会继续监听，所以永真循环
            while (true) {
                Socket socket = ss.accept(); //若没有客户端连接，就阻塞在这里
                //socket关联的对象输入流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //socket关联的对象输出流
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                User u = (User) ois.readObject();//读取客户端发来的User对象
                //创建个Message对象回复客户端
                Message message = new Message();

                // TODO: DB数据库验证
                if (dbUserAccount.checkUser(u.getUserId(), u.getPasswd())) { //登录成功
                    message.setMesStatus(MessageStatus.MESSAGE_LOGIN_SUCCEED);
                    //将msg回复给客户端
                    oos.writeObject(message);
                    //创建一个和客户端保持通信的 持有socket对象的 线程
                    ServerConnectClientThread serverConnectClientThread
                            = new ServerConnectClientThread(socket, u.getUserId(), this);
                    //启动线程
                    serverConnectClientThread.start();
                    //该线程放入一个集合中进行管理
                    ManageClientThreads.addClientThread(u.getUserId(), serverConnectClientThread);
                    updataList();

                } else { //登录失败
                    message.setMesStatus(MessageStatus.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    //关闭socket
                    socket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //若服务器退出了while，说明服务端不再监听=>关闭ServerSocket
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updataList() {
//        DBUserAccount.validUsers.
        ta_list.setText(ManageClientThreads.getOnlineUser());
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        ta_diary = new JTextArea();
        label1 = new JLabel();
        btn_exit = new JButton();
        scrollPane2 = new JScrollPane();
        ta_list = new JTextArea();

        //======== this ========
        setTitle("\u670d\u52a1\u7aef");
        var contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- ta_diary ----
            ta_diary.setEnabled(false);
            ta_diary.setForeground(new Color(51, 51, 51));
            ta_diary.setDisabledTextColor(new Color(51, 51, 51));
            scrollPane1.setViewportView(ta_diary);
        }

        //---- label1 ----
        label1.setText("\u7528\u6237\u5217\u8868");

        //---- btn_exit ----
        btn_exit.setText("\u5173\u95ed");

        //======== scrollPane2 ========
        {

            //---- ta_list ----
            ta_list.setEnabled(false);
            ta_list.setForeground(new Color(51, 51, 51));
            ta_list.setDisabledTextColor(new Color(51, 51, 51));
            scrollPane2.setViewportView(ta_list);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(292, Short.MAX_VALUE)
                    .addComponent(btn_exit)
                    .addGap(28, 28, 28))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btn_exit)
                    .addGap(11, 11, 11))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTextArea ta_diary;
    private JLabel label1;
    private JButton btn_exit;
    private JScrollPane scrollPane2;
    private JTextArea ta_list;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JTextArea getTa_diary() {
        return ta_diary;
    }
}
