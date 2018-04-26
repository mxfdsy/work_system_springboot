package com.coding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication()
@MapperScan(value = "com.coding.user.dao")
public class WorkSystemSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkSystemSpringbootApplication.class, args);
	}
}
