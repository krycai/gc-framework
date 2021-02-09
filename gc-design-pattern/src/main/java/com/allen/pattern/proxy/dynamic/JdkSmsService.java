package com.allen.pattern.proxy.dynamic;

/**
 * Created by xuguocai on 2021/2/9 13:56  定义发送短信的接口
 */
public interface JdkSmsService {
    String send(String message);
}
