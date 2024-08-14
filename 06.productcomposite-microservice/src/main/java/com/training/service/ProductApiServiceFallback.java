package com.training.service;

import org.springframework.stereotype.Component;

import com.training.entity.Product;

@Component
public class ProductApiServiceFallback implements ProductApiService {

	public Product getProduct(int productId) {
		System.out.println("-----------------------------------------");
		Product p = new Product();
		p.setName("iPhone 15 Cached");
		p.setPrice(79000);
		return p;
	}
}
