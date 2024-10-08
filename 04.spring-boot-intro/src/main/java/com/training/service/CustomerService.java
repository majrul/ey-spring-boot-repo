package com.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.training.entity.Address;
import com.training.entity.Customer;
import com.training.exception.CustomerServiceException;
import com.training.repository.AddressRepository;
import com.training.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	/*@Autowired
	public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
		this.customerRepository = customerRepository;
		this.addressRepository = addressRepository;
	}*/
	
	@CachePut(value = "customer.cache", key = "#customer.id")
	@CacheEvict("customers.cache")
	public Customer register(Customer customer) {
		//first check if customer has already registered
		Optional<Customer> optionalCustomer = customerRepository.findByEmail(customer.getEmail());
		if(optionalCustomer.isPresent())
			throw new CustomerServiceException("Customer already registered!");
		else {
			Address address = customer.getAddress();
			address.setCustomer(customer);
			customerRepository.save(customer);
			return customer;
			//send a welcome email to the customer's email address
			//emailService.sendWelcomeMail(customer.getEmail());
		}
	}
	
	@Cacheable("customers.cache") //region
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}
	
	@Cacheable(value = "customer.cache", key = "#id")
	public Customer get(int id) {
		Optional<Customer> c = customerRepository.findById(id);
		if(c.isPresent())
			return c.get();
		else
			throw new CustomerServiceException("No customer with id " + id);				
	}
	
}
