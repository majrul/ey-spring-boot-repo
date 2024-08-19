package com.training.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

//@Component
@Service
//business logic
public class ProductService {

	@Autowired //DI
	//@Qualifier("repo2")
	private ProductRepository productRepository;// = new ProductRepositoryImpl();
	
	public ProductService() {
		System.out.println("ProductService()");
	}
	
	@PostConstruct
	public void init() {
		productRepository.doSomething();
	}
}
