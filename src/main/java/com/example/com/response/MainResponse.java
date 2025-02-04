package com.example.com.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class MainResponse {

    private String message;
    private Object result;


    public MainResponse(String message, Object result) {
        this.message = message;
        this.result = result;


    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return result;
    }

    public void setObj(Object obj) {
        this.result = obj;
    }
}
