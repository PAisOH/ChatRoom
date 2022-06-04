/*
 * Created by JFormDesigner on Wed Jun 01 18:57:12 CST 2022
 */

package com.test.client.view;
import javax.swing.*;
import java.awt.*;

/**
 * @author Autumn
 */
public class LoginView {
    private LoginHandler loginHandler;

    public static void main(String[] args) {
        new LoginView();
        System.out.println("客户端退出系统...");
    }

    public LoginView() {
        initComponents();
        loginHandler = new LoginHandler(this);
        btn_login.addActionListener(loginHandler);
        btn_exit.addActionListener(loginHandler);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        JF_login = new JFrame();
        jlabel = new JLabel();
        jlabel_psw = new JLabel();
        btn_login = new JButton();
        btn_exit = new JButton();
        tf_account = new JTextField();
        tf_psw = new JTextField();
        label1 = new JLabel();
        jlabel_notice = new JLabel();

        //======== JF_login ========
        {
            JF_login.setTitle("\u767b\u5f55");
            JF_login.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 12));
            JF_login.setVisible(true);
            var JF_loginContentPane = JF_login.getContentPane();

            //---- jlabel ----
            jlabel.setText("\u540d\u79f0");

            //---- jlabel_psw ----
            jlabel_psw.setText("\u5bc6\u7801");

            //---- btn_login ----
            btn_login.setText("\u767b\u5f55");

            //---- btn_exit ----
            btn_exit.setText("\u91cd\u7f6e");

            //---- label1 ----
            label1.setText("\u5ba2\u6237\u7aef\u767b\u5f55");
            label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));

            //---- jlabel_notice ----
            jlabel_notice.setText("\u6b22\u8fce\u4f7f\u7528\u804a\u5929\u5ba4");
            jlabel_notice.setHorizontalAlignment(SwingConstants.CENTER);

            GroupLayout JF_loginContentPaneLayout = new GroupLayout(JF_loginContentPane);
            JF_loginContentPane.setLayout(JF_loginContentPaneLayout);
            JF_loginContentPaneLayout.setHorizontalGroup(
                JF_loginContentPaneLayout.createParallelGroup()
                    .addGroup(JF_loginContentPaneLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(JF_loginContentPaneLayout.createParallelGroup()
                            .addGroup(JF_loginContentPaneLayout.createSequentialGroup()
                                .addGroup(JF_loginContentPaneLayout.createParallelGroup()
                                    .addGroup(JF_loginContentPaneLayout.createSequentialGroup()
                                        .addComponent(jlabel_psw, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tf_psw, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(JF_loginContentPaneLayout.createSequentialGroup()
                                        .addComponent(jlabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(JF_loginContentPaneLayout.createParallelGroup()
                                            .addComponent(label1)
                                            .addComponent(tf_account, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(81, Short.MAX_VALUE))
                            .addGroup(JF_loginContentPaneLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(btn_login)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                                .addComponent(btn_exit)
                                .addGap(79, 79, 79))))
                    .addComponent(jlabel_notice, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
            );
            JF_loginContentPaneLayout.setVerticalGroup(
                JF_loginContentPaneLayout.createParallelGroup()
                    .addGroup(JF_loginContentPaneLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(label1)
                        .addGap(30, 30, 30)
                        .addGroup(JF_loginContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(jlabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_account, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JF_loginContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jlabel_psw, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_psw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlabel_notice)
                        .addGap(18, 18, 18)
                        .addGroup(JF_loginContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_login)
                            .addComponent(btn_exit))
                        .addContainerGap(15, Short.MAX_VALUE))
            );
            JF_login.pack();
            JF_login.setLocationRelativeTo(JF_login.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JFrame JF_login;
    private JLabel jlabel;
    private JLabel jlabel_psw;
    private JButton btn_login;
    private JButton btn_exit;
    private JTextField tf_account;
    private JTextField tf_psw;
    private JLabel label1;
    private JLabel jlabel_notice;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JTextField getTf_account() {
        return tf_account;
    }

    public void setTf_account(JTextField tf_account) {
        this.tf_account = tf_account;
    }

    public JTextField getTf_psw() {
        return tf_psw;
    }

    public void setTf_psw(JTextField tf_psw) {
        this.tf_psw = tf_psw;
    }

    public JLabel getJlabel_notice() {
        return jlabel_notice;
    }

    public void dispose(){
        JF_login.dispose();
    }

    public JButton getBtn_login() {
        return btn_login;
    }
}
