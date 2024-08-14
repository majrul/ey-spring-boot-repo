package com.training.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Customer {

	@Id
	private String id;
	
	private String name;
	private String email;
	private LocalDate dateOfBirth;
	private String city;
	
}
