package com.training;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.training.entity.Product;
import com.training.repository.ProductRepository;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	void addProduct() {
		Product p = new Product();
		p.setName("iPhone 12");
		p.setPrice(49000);
		p.setQuantity(99);
		productRepository.save(p);
		
		//assertion is missing
	}
	
	@Test
	void fetchProducts() {
		List<Product> list = productRepository.findAll();
		assertEquals(3, list.size());
	}

}
