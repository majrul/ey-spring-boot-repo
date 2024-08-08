package com.training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.entity.Customer;
import com.training.service.CustomerService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class CustomerControllerTest5 {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void getCustomer() throws Exception {
		Customer expectedCustomer = new Customer();
		expectedCustomer.setId(6);
		expectedCustomer.setName("Majrul");
		expectedCustomer.setEmail("majrul@gmail.com");
		expectedCustomer.setPassword("majrul123");
		
		ObjectMapper mapper = new ObjectMapper();
		String expectedJson = mapper.writeValueAsString(expectedCustomer);
		
		MvcResult result = mockMvc
							.perform(get("/customer/{id}", 6))
							.andDo(print())
							.andExpect(status().isOk())
							.andReturn();
		
		String actualJson = result.getResponse().getContentAsString();
		Customer actualCustomer = mapper.readValue(actualJson, Customer.class);
		
		assertEquals(expectedCustomer, actualCustomer);
							
	}
	
}
