package com.allen.upload;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.**.mapper"})
public class UploadApp {

    public static void main(String[] args) {
        SpringApplication.run(UploadApp.class, args);
    }

}
