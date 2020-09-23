package com.allen.mycat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(value = "com.allen.mycat.mapper")
public class MycatApp {

	public static void main(String[] args) {
		SpringApplication.run(MycatApp.class, args);
	}

}
