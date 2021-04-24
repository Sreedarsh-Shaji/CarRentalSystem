package com.fariz.carrental.messages;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Message {

    @Getter @Setter
    private MessageType messageTitle;
    @Getter @Setter
    private String messageBody;
    @Getter @Setter
    private Date createTime;

    private static Message messageInstance = null;

    private Message(){}

    public static void createMessage()
    {
        messageInstance = new Message();
        System.out.println("["+ new Date() +"] : Message Initialized");
    }

    public static Message setMessage(MessageType mTitle, String mBody, Date cTime)
    {
        messageInstance.messageTitle = mTitle;
        messageInstance.messageBody = mBody;
        messageInstance.createTime = cTime;

        return messageInstance;
    }

    public static Message getMessage()
    {
        return messageInstance;
    }

}
