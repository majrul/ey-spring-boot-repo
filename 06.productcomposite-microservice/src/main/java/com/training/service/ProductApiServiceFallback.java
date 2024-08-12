package com.training.service;

import com.training.entity.Product;

public class ProductApiServiceFallback implements ProductApiService {

	public Product getProduct(int productId) {
		Product p = new Product();
		p.setName("iPhone 15 Cached");
		p.setPrice(79000);
		return p;
	}
}
