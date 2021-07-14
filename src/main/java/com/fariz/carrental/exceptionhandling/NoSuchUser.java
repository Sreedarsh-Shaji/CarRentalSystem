package com.fariz.carrental.exceptionhandling;

import com.fariz.carrental.messages.Message;
import com.fariz.carrental.messages.MessageType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "no such User")
public class NoSuchUser extends RuntimeException {
    public NoSuchUser() {
        super();
        Message.setMessage(MessageType.ERROR, "The entity with specified id doesn't exist!", new Date());
    }

}
