package com.training;

import java.util.HashMap;
import java.util.Map;

import javax.swing.text.rtf.RTFEditorKit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.training.dto.RegisterStatus;
import com.training.entity.Address;
import com.training.entity.Customer;

@SpringBootTest
class CustomerControllerTest {

	@Test
	void register() {
		RestTemplate rt = new RestTemplate();
		String url = "http://localhost:8080/customer/register";
		
		Customer customer = new Customer();
		customer.setName("Majrul");
		customer.setEmail("majrul@gmail.com");
		customer.setPassword("majrul123");
		
		Address address = new Address();
		address.setCity("Mumbai");
		address.setPincode(40001);
		
		customer.setAddress(address);
		
		HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(customer);
		
		//RegisterStatus status = rt.postForObject(url, customer, RegisterStatus.class);
		//RegisterStatus status = rt.postForObject(url, requestEntity, RegisterStatus.class);
		ResponseEntity<RegisterStatus> response = rt.postForEntity(url, requestEntity, RegisterStatus.class);
		RegisterStatus status = response.getBody();

		System.out.println(response.getStatusCode());
		System.out.println(status.getStatusMessage());
	}
	
	@Test
	void registerv3() {
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		String url = "http://localhost:8080/customer/registerv3";
		
		String customerData = "{\n"
				+ "    \"name\" : \"John\",\n"
				+ "    \"email\" : \"john@gmail.com\",\n"
				+ "    \"password\" : \"john123\",\n"
				+ "    \"address\" : {\n"
				+ "        \"city\" : \"Mumbai\",\n"
				+ "        \"pincode\" : 40001\n"
				+ "    }\n"
				+ "}";
		
		Resource profilePic = new ClassPathResource("john.jpg");
		
		//Map<String, Object> map = new HashMap<>();
		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("customer", customerData);
		map.add("profilePic", profilePic);
		
		HttpEntity<LinkedMultiValueMap<String,Object>> requestEntity = new HttpEntity<LinkedMultiValueMap<String,Object>>(map, headers);
		
		ResponseEntity<String> response = rt.postForEntity(url, requestEntity, String.class);
		System.out.println(response.getBody());
	}

}
