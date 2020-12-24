package com.allen.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(value = "com.allen.sharding.mapper")
public class ShardingJdbcApp {

	public static void main(String[] args) {
		SpringApplication.run(ShardingJdbcApp.class, args);
	}

}
