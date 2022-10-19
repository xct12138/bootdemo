package com.xct.bootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.xct.bootdemo.dao")
public class BootdemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BootdemoApplication.class, args);
	}
	
}
