package com.test.client.service;

import com.test.common.Message;
import com.test.common.MessageStatus;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * @Data: 2022-06-02
 * @Start Time: 23:24
 * 提供和 消息 相关的服务方法
 */


public class MessageClientService {

    /**
     * 群聊客户端实现
     * @param content 群发消息
     * @param senderId 发送者Id
     */
    public void sendMessageToAll(String content, String senderId){
        Message message = new Message();
        message.setMesStatus(MessageStatus.MESSAGE_TO_ALL_MES); //群发消息
        message.setSender(senderId);
        message.setContent(content);
        message.setSendTime(new Date().toString()); //发送时间设置到msg对象
        System.out.println(senderId + " 对 大家说: " + content);
        try {
            //拿到 发送方线程里的 socket里的 输出流oos
            ObjectOutputStream oos = new ObjectOutputStream(ManagerClientConnectServerThread.
                    getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 私聊客户端实现
     * @param content 内容
     * @param senderId 发送用户Id
     * @param getterId 接收用户Id
     */
    public void sendMessageToOne(String content, String senderId, String getterId){
        Message message = new Message();
        message.setMesStatus(MessageStatus.MESSAGE_COMM_MES); //普通聊天消息
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setContent(content);
        message.setSendTime(new Date().toString()); //发送时间设置到msg对象
        System.out.println(senderId + " 对 " + getterId + "说: " + content);
        System.out.println(senderId + " 对 " +  getterId +"(我)说: " + content);
        try {
            //拿到 发送方线程里的 socket里的 输出流oos
            ObjectOutputStream oos = new ObjectOutputStream(ManagerClientConnectServerThread.
                            getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
