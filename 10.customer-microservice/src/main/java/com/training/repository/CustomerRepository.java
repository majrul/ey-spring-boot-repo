package com.training.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.training.entity.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
	
	public Optional<Customer> findByEmail(String email);
}
