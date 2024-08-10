package com.training;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//System.setProperty("spring.profiles.active", "dev");
		SpringApplication.run(Application.class, args);
	}

}

@Component
class SomeRepository {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private StorageService storageService;
	
	@PostConstruct
	public void init() {
		System.out.println(storageService.getClass());
		try {
			Connection conn = dataSource.getConnection();
			System.out.println("got connected to " + conn.getMetaData().getDatabaseProductName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
