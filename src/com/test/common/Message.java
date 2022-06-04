package com.test.common;

import java.io.Serializable;

/**
 * @Data: 2022-05-27
 * @Start Time: 20:37
 * 客户端和服务器端通信时的消息对象
 */


public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sender;//发送者
    private String getter;//接受者
    private String content;//消息内容
    private String sendTime;//发送时间也会序列化,所以用String
    private String mesStatus; //消息类型[接口中定义消息类型]


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMesStatus() {
        return mesStatus;
    }

    public void setMesStatus(String mesStatus) {
        this.mesStatus = mesStatus;
    }
}
