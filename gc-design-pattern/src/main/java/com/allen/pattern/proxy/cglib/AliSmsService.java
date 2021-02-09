package com.allen.pattern.proxy.cglib;

/**
 * Created by xuguocai on 2021/2/9 13:46
 */
public class AliSmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
