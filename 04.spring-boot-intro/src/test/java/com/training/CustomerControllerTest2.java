package com.training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.training.controller.CustomerController;
import com.training.entity.Customer;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class CustomerControllerTest2 {

	@Autowired
	private CustomerController customerController;
	
	@Test
	void getAll() {
		List<Customer> list = customerController.getAll();
		assertNotNull(list);
	}
	
	@Test
	void get() {
		Customer c = customerController.get(6);
		assertEquals("Majrul", c.getName());
	}


}
