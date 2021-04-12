package com.allen.netty.server;

/**
 * Created by xuguocai on 2021/4/12 17:21
 */
@ServletComponentScan//防止 @WebListener 无效
@SpringBootApplication
public class ServerApp {
    public static void main(String[] args) {
        SpringApplication.run(ServerApp.class, args);
    }
}
