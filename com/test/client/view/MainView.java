/*
 * Created by JFormDesigner on Fri Jun 03 16:36:14 CST 2022
 */

package com.test.client.view;
import com.test.client.service.UserClientService;
import com.test.common.User;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Autumn
 */
public class MainView extends JFrame {
    private MainHandler mainHandler;

    public MainView(String userId) {
        initComponents();
        setTitle(userId);
        mainHandler = new MainHandler(this);
        btn_send.addActionListener(mainHandler);
        btn_exit.addActionListener(mainHandler);
    }

    public MainView(String userId, UserClientService userClientService) {
        initComponents();
        setTitle(userId);
        mainHandler = new MainHandler(this, userClientService);
        btn_send.addActionListener(mainHandler);
        btn_exit.addActionListener(mainHandler);
        btn_reload_list.addActionListener(mainHandler);
        userClientService.onlineFriendList();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        ta_output = new JTextArea();
        scrollPane2 = new JScrollPane();
        ta_input = new JTextArea();
        btn_exit = new JButton();
        scrollPane3 = new JScrollPane();
        ta_list = new JTextArea();
        tf_perUserId = new JTextField();
        btn_send = new JButton();
        btn_reload_list = new JButton();

        //======== this ========
        setVisible(true);
        var contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- ta_output ----
            ta_output.setDisabledTextColor(new Color(51, 51, 51));
            ta_output.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1 Light", Font.PLAIN, 12));
            ta_output.setEnabled(false);
            scrollPane1.setViewportView(ta_output);
        }

        //======== scrollPane2 ========
        {

            //---- ta_input ----
            ta_input.setDisabledTextColor(new Color(0, 153, 153));
            ta_input.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1 Light", Font.PLAIN, 17));
            scrollPane2.setViewportView(ta_input);
        }

        //---- btn_exit ----
        btn_exit.setText("\u9000\u51fa");
        btn_exit.setBackground(new Color(153, 51, 0));

        //======== scrollPane3 ========
        {

            //---- ta_list ----
            ta_list.setEnabled(false);
            ta_list.setDisabledTextColor(new Color(0, 102, 102));
            scrollPane3.setViewportView(ta_list);
        }

        //---- tf_perUserId ----
        tf_perUserId.setHorizontalAlignment(SwingConstants.CENTER);

        //---- btn_send ----
        btn_send.setText("\u53d1\u9001");
        btn_send.setBackground(new Color(0, 153, 153));

        //---- btn_reload_list ----
        btn_reload_list.setText("\u5237\u65b0\u7528\u6237\u5217\u8868");
        btn_reload_list.setBackground(new Color(102, 102, 255));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(btn_exit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_send, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(tf_perUserId, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(scrollPane3))
                            .addGap(18, 18, 18))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(btn_reload_list, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(btn_reload_list, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tf_perUserId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addGap(18, 18, 18)))
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(btn_send, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_exit, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
                    .addGap(15, 15, 15))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTextArea ta_output;
    private JScrollPane scrollPane2;
    private JTextArea ta_input;
    private JButton btn_exit;
    private JScrollPane scrollPane3;
    private JTextArea ta_list;
    private JTextField tf_perUserId;
    private JButton btn_send;
    private JButton btn_reload_list;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public JTextArea getTa_output() {
        return ta_output;
    }

    public JTextArea getTa_input() {
        return ta_input;
    }

    public JTextArea getTa_list() {
        return ta_list;
    }

    public JButton getBtn_exit() {
        return btn_exit;
    }

    public JTextField getTf_perUserId() {
        return tf_perUserId;
    }

    public JButton getBtn_send() {
        return btn_send;
    }

}
