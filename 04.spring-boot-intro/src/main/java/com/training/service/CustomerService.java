package com.training.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public int register(Customer customer) {
		//first check if customer has already registered
		Optional<Customer> optionalCustomer = customerRepository.findByEmail(customer.getEmail());
		if(optionalCustomer.isPresent())
			throw new CustomerServiceException("Customer already registered!");
		else {
			Address address = customer.getAddress();
			address.setCustomer(customer);
			customerRepository.save(customer);
			return customer.getId();
			//send a welcome email to the customer's email address
			//emailService.sendWelcomeMail(customer.getEmail());
		}
	}
	
}
