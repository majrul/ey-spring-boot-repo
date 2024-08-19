package com.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.xyz.component")
public class Application {

	public static void main(String[] args) {
		//System.setProperty("spring.profiles.active", "prod");
		SpringApplication.run(Application.class, args);
	}

}
