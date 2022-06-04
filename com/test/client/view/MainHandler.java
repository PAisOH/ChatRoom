package com.test.client.view;

import com.test.client.service.MessageClientService;
import com.test.client.service.UserClientService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Data: 2022-06-03
 * @Start Time: 16:37
 * 主动：监听客户端操作
 */


public class MainHandler implements ActionListener {
    private MessageClientService messageClientService; //对象用户私聊/群聊
    private MainView mainView;
    private UserClientService userClientService;

    public MainHandler(MainView mainView, UserClientService userClientService) {
        this.mainView = mainView;
        this.userClientService = userClientService;
        messageClientService = new MessageClientService();
    }
    public MainHandler(MainView mainView) {
        this.mainView = mainView;
        messageClientService = new MessageClientService();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton)e.getSource();
        String text = jButton.getText();
        if ("发送".equals(text)) {
            //聊天框显示要发送内容
            String content = mainView.getTa_input().getText();
            //判空
            if(!content.isBlank()) {
                String senderId = mainView.getTitle();
                String getterId = mainView.getTf_perUserId().getText();
                if (content != null) mainView.getTa_output().append(senderId + "(我):" + content + "\n");
                //清除书写框的内容
                mainView.getTa_input().setText("");
                //私发
                if (!mainView.getTf_perUserId().getText().isBlank()) {
                    messageClientService.sendMessageToOne(content, senderId, getterId);
                }
                //群发
                else {
                    messageClientService.sendMessageToAll(content, senderId);
                }
            }else {
                //空内容提示
                JOptionPane.showMessageDialog(mainView, "请输入内容再发送!");
            }
        }
        if("退出".equals(text)) {
            userClientService.onlineFriendList();
            userClientService.logOut();
            mainView.dispose();
        }
        if("刷新用户列表".equals(text)){
            userClientService.onlineFriendList();
        }
    }
}
