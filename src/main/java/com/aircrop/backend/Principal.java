package com.aircrop.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.aircrop.backend")
public class Principal {

	public static void main(String[] args) {
		SpringApplication.run(Principal.class, args);
	}
}
