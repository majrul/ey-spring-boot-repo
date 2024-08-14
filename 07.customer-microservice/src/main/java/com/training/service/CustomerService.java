package com.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.dto.CustomerDto;
import com.training.entity.Customer;
import com.training.exception.CustomerServiceException;
import com.training.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public String register(Customer customer) {
        //first check if there is no other customer with the same email address
        //if record already exists, throw an exception
        //else save the customer in the database
        //then return the generated customer id
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customer.getEmail());
        if(optionalCustomer.isPresent())
            throw new CustomerServiceException("Customer already registered!");
        else {
            customer = customerRepository.save(customer);
            return customer.getId();
        }
    }

    public Customer fetchById(String id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerServiceException("No such customer!"));
    }

	public List<Customer> fetchAll() {
		return customerRepository.findAll();
	}

    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    public void partialUpdate(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerDto.getId());
        if(optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if(customerDto.getPropertyName().equals("name"))
                customer.setName(customerDto.getPropertyValue());
            else if(customerDto.getPropertyName().equals("email"))
                customer.setEmail(customerDto.getPropertyValue());
            else if(customerDto.getPropertyName().equals("city"))
                customer.setCity(customerDto.getPropertyValue());
            customerRepository.save(customer);
        }
        else
            throw new CustomerServiceException("No such customer!");
    }

    public void delete(String id) {
        customerRepository.deleteById(id);
    }

}