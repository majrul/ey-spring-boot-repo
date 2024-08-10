package com.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.entity.Product;
import com.training.exception.ProductException;
import com.training.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product getProduct(int id) {
		return productRepository
				.findById(id)
				.orElseThrow(() -> new ProductException("No product for id " + id));
	}
}
