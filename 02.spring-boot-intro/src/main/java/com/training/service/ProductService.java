package com.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.entity.Product;
import com.training.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public int add(Product product) {
		productRepository.save(product);
		return product.getId();
	}
	
	public List<Product> fetchAll() {
		return productRepository.findAll();
	}
	
	public Product fetch(int id) {
		Optional<Product> p = productRepository.findById(id);
		if(p.isPresent())
			return p.get();
		else
			return null; //bad, rather throw some exception
	}
}