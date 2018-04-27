package com.coding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@SpringBootApplication()
@MapperScan(value = "com.coding.user.dao")
public class WorkSystemSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkSystemSpringbootApplication.class, args);
	}
}
