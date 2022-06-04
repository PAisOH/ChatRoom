package com.test.client.view;



import com.test.client.service.ManagerClientConnectServerThread;
import com.test.client.service.UserClientService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Data: 2022-06-01
 * @Start Time: 18:53
 * 处理Login界面事件
 */


public class LoginHandler implements ActionListener {
    private LoginView loginView;
    private UserClientService userClientService = new UserClientService();//用于登录服务器,注册用户

    public LoginHandler(LoginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton)e.getSource();
        String text = jButton.getText();

        String userId = loginView.getTf_account().getText();
        String pwd = loginView.getTf_psw().getText();
        if(userClientService.checkUser(userId, pwd)){
            loginView.getJlabel_notice().setText("登录成功");
            userClientService.onlineFriendList();
            loginView.dispose();
            //不在这里new，不然后面不好传
            MainView mainView = new MainView(userId, userClientService);
            //将所有用户界面，存入static
            ManagerClientConnectServerThread.addMainView(userId, mainView);
        } else {
            loginView.getJlabel_notice().setText("登录失败");
        }
    }
}
