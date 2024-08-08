package com.training.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.dto.CustomerDetails;
import com.training.dto.RegisterStatus;
import com.training.entity.Customer;
import com.training.exception.CustomerServiceException;
import com.training.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer/all")
	public List<Customer> getAll() {
		return customerService.getAll();
	}
	
	@GetMapping("/customer/{id}")
	public Customer get(@PathVariable int id) {
		return customerService.get(id);
	}
	
	@PostMapping("/customer/register")
	public RegisterStatus register(@RequestBody Customer customer) {
		//System.out.println(customer.getAddress());
		//System.out.println(customer.getAddress().getCustomer());
		try {
			int id = customerService.register(customer);
			RegisterStatus status = new RegisterStatus();
			status.setStatus(true);
			status.setStatusMessage("Customer registered!");
			status.setRegisteredCustomerId(id);
			return status;
		}
		catch(CustomerServiceException e) {
			RegisterStatus status = new RegisterStatus();
			status.setStatus(false);
			status.setStatusMessage(e.getMessage());
			return status;
		}
	}
	
	//@PostMapping(path = "/customer/registerv2", consumes = "multipart/form-data")
	@PostMapping("/customer/registerv2")
	public RegisterStatus registerv2(CustomerDetails customerDetails) {
		//System.out.println(customer.getAddress());
		//System.out.println(customer.getAddress().getCustomer());
		try {
			//copy data from dto to entity
			Customer customer = new Customer();
			BeanUtils.copyProperties(customerDetails, customer);

			//store the uploaded file
			try {
				String fileName = customerDetails.getProfilePic().getOriginalFilename();
				String generatedFileName = fileName; //code to generate a unique name for the file
				
				customer.setProfilePic(generatedFileName);
				
				InputStream is = customerDetails.getProfilePic().getInputStream();
				FileOutputStream os = new FileOutputStream("c:/uploads/" + generatedFileName);
				FileCopyUtils.copy(is, os);
			}
			catch (Exception e) {
				//hoping no error will occur hence keeping it block
			}
			
			int id = customerService.register(customer);
			
			RegisterStatus status = new RegisterStatus();
			status.setStatus(true);
			status.setStatusMessage("Customer registered!");
			status.setRegisteredCustomerId(id);
			return status;
		}
		catch(CustomerServiceException e) {
			RegisterStatus status = new RegisterStatus();
			status.setStatus(false);
			status.setStatusMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping(path = "/customer/registerv3", consumes = "multipart/form-data")
	public RegisterStatus registerv3(@RequestPart("customer") String customerData,
									 @RequestPart("profilePic") MultipartFile file) {

		try {
			//copy data from dto to entity
			Customer customer = new ObjectMapper().readValue(customerData, Customer.class);
			
			//store the uploaded file
			try {
				String fileName = file.getOriginalFilename();
				String generatedFileName = fileName; //code to generate a unique name for the file
				
				customer.setProfilePic(generatedFileName);
				
				InputStream is = file.getInputStream();
				FileOutputStream os = new FileOutputStream("c:/uploads/" + generatedFileName);
				FileCopyUtils.copy(is, os);
			}
			catch (Exception e) {
				//hoping no error will occur hence keeping it block
			}
			
			int id = customerService.register(customer);
			
			RegisterStatus status = new RegisterStatus();
			status.setStatus(true);
			status.setStatusMessage("Customer registered!");
			status.setRegisteredCustomerId(id);
			return status;
		}
		catch(CustomerServiceException | JsonProcessingException e) {
			RegisterStatus status = new RegisterStatus();
			status.setStatus(false);
			status.setStatusMessage(e.getMessage());
			return status;
		}
	}
}
