package com.allen.pattern.proxy.dynamic;

/**
 * Created by xuguocai on 2021/2/9 14:01  实际使用
 */
public class JdkMain {

    public static void main(String[] args) {
        JdkSmsService smsService = (JdkSmsService) JdkProxyFactory.getProxy(new JdkSmsServiceImpl());
        smsService.send("java");
    }
}
