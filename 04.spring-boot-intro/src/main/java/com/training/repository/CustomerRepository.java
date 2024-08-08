package com.training.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Optional<Customer> findByEmail(String email);
}
