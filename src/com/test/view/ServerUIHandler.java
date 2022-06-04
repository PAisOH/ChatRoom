package com.test.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Data: 2022-06-03
 * @Start Time: 15:56
 */


public class ServerUIHandler implements ActionListener {
    private ServerUI serverUI;

    public ServerUIHandler(ServerUI serverUI) {
        this.serverUI = serverUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton)e.getSource();
        String text = jButton.getText();
        if("关闭".equals(text)) {
            serverUI.dispose();
            System.out.println("服务器关闭");
            System.exit(0);
        }
    }
}
