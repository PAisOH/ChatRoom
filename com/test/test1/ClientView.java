package com.test.test1;

import com.test.client.service.MessageClientService;
import com.test.client.service.UserClientService;
import com.test.client.utils.Utility;

/**
 * @Data: 2022-05-27
 * @Start Time: 20:57
 */


public class ClientView {
    private boolean loop = true; //是否显示菜单
    private String key = "";//用户键盘输入
    private UserClientService userClientService = new UserClientService();//用于登录服务器,注册用户
    private MessageClientService messageClientService = new MessageClientService(); //对象用户私聊/群聊

    public static void main(String[] args) {
        new ClientView().mainMenu();
        System.out.println("客户端退出系统...");
    }

    //显示主菜单
    private void mainMenu(){
        while(loop){
            //TODO: 没意义--这个登录退出，做UI时删除
            System.out.println("==================================");
            System.out.println("\t\t 1.登录");
            System.out.println("\t\t 9.退出");
            System.out.print("请输入您的选择: ");
            key = Utility.readString(1); //读取一个字符串

            switch(key){
                case "1":
                    System.out.print("请输入用户号: ");
                    String userId = Utility.readString(50);
                    System.out.print("请输入密 码: ");
                    String pwd = Utility.readString(50);
                    //!!!!!!!!需要到服务端验证该用户是否合法，有很多代码=>编写一个类UserClientService[用户登入的服务]
                    //在userClientService中校正用户信息，启动线程
                    if(userClientService.checkUser(userId, pwd)){
                        System.out.println("=============欢迎"+ userId + "登入成功============");
                        //已经进入到二级菜单了
                        while(loop){
                            System.out.println("\n==============二级菜单(用户" + userId + ")============");
                            System.out.println("\t\t 1.显示在线用户列表");
                            System.out.println("\t\t 2.群发消息");
                            System.out.println("\t\t 3.私聊消息");
                            System.out.println("\t\t 4.发送文件");
                            System.out.println("\t\t 9.退出系统");
                            System.out.print("请输入您的选择: ");
                            key = Utility.readString(1);
                            switch (key){
                                case "1":
                                    System.out.println("显示用户");
                                    userClientService.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.println("请输入想群发的消息: ");
                                    String s = Utility.readString(100);
                                    //将消息封装成msg对象，发送给服务端
                                    messageClientService.sendMessageToAll(s, userId);
                                    System.out.println("群发消息");
                                    break;
                                case "3":
                                    System.out.println("请输入想聊天的用户号: ");
                                    String getterId = Utility.readString(50);
                                    System.out.println("请发送消息: ");
                                    String content = Utility.readString(50);
                                    //将私聊消息发送给服务器端
                                    messageClientService.sendMessageToOne(content, userId, getterId);
                                    System.out.println("私聊消息 ");
                                    break;
                                case "4":
                                    System.out.println("发送文件");
                                    break;
                                case "9":
                                    //给服务器一个退出的message，切断通信线程
                                    userClientService.logOut();
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("请输入1,2,3,4,9中的一位数字");
                            }
                        }
                    } else {
                        System.out.println("登录失败:(");
                    }

                    break;
                case "9":
                    loop = false;
                    break;
                default:
                    System.out.println("请输入1或9");
            }
            System.out.println("==================================");
        }
    }
}
