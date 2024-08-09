package com.training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.training.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Optional<Customer> findByEmail(String email);

	@Query("select c from Customer c where c.email like %?1%")
	public Optional<Customer> fetchByEmail(String email);
	
	@Query("select c from Customer c join fetch c.address a where a.city = ?1")
	public List<Customer> findByCity(String city);

	/*@Modifying
	@Query("update Customer c set c.active = false where c.id = ?1")
	void deleteCustomer(int id);*/
	
	@Query("select count(c) from Customer c join c.address a where a.city = ?1")
	public Long findHowManyCustomersFromCity(String city);
	
	@Query("select c from Customer c join fetch c.address a where a.city = ?1")
	Page<Customer> findByCity(String city, Pageable pageable);
}

