package com.allen.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by xuguocai on 2021/3/15 9:29
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class RedisApp {

    public static void main(String[] args) {
        SpringApplication.run(RedisApp.class, args);
    }

}
