package com.training;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableConfigServer
public class Application {

	public static void main(String[] args) {
		//System.setProperty("spring.profiles.active", "native,vault");
		SpringApplication.run(Application.class, args);
	}

}
