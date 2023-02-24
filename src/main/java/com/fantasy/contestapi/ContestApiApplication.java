package com.fantasy.contestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ContestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContestApiApplication.class, args);
	}

}
