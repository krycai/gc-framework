package com.allen.pattern.proxy.dynamic;

/**
 * Created by xuguocai on 2021/2/9 13:57  实现发送短信的接口
 */
public class JdkSmsServiceImpl implements JdkSmsService {
    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
