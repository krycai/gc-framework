package com.allen.pattern.proxy.cglib;

/**
 * Created by xuguocai on 2021/2/9 13:49
 */
public class AliCglibMain {

    public static void main(String[] args) {
        AliSmsService aliSmsService = (AliSmsService) AliCglibProxyFactory.getProxy(AliSmsService.class);
        aliSmsService.send("java");
    }

}
