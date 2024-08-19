package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.CustomerDto;
import com.training.dto.RegisterStatus;
import com.training.dto.Status;
import com.training.entity.Customer;
import com.training.service.CustomerService;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer/register")
    public ResponseEntity<RegisterStatus> register(@RequestBody Customer customer) {
        String id = customerService.register(customer);
        RegisterStatus registerStatus = new RegisterStatus();
        registerStatus.setStatusCode(100); //some superficial code
        registerStatus.setMessageIfAny("Registration successful!");
        registerStatus.setRegisteredCustomerId(id);
        return ResponseEntity
                .ok()
                //.header("cache-control", "no-cache")
                //.header("csrf-token", "jhsdfgjhsd23213dsjkj")
                .body(registerStatus);
    }

    @GetMapping("/customer/{id}")
    public Customer fetch(@PathVariable String id) {
        return customerService.fetchById(id);
    }

    @GetMapping("/customer/all")
    public List<Customer> fetchAll() {
        return customerService.fetchAll();
    }

    @PutMapping("/customer/update")
    public Status update(@RequestBody  Customer customer) {
        customerService.update(customer);
        Status status = new Status();
        status.setStatusCode(200); //again some superficial code
        status.setMessageIfAny("Customer updated!");
        return status;
    }

    @PatchMapping("/customer/patch")
    public Status update(@RequestBody CustomerDto customerDto) {
        customerService.partialUpdate(customerDto);
        Status status = new Status();
        status.setStatusCode(200); //again some superficial code
        status.setMessageIfAny("Customer updated!");
        return status;
    }

    @DeleteMapping("/customer/delete/{id}")
    public Status update(@PathVariable String id) {
        customerService.delete(id);
        Status status = new Status();
        status.setStatusCode(300); //again some superficial code
        status.setMessageIfAny("Customer deleted!");
        return status;
    }
}
