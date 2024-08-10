package com.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.entity.Product;
import com.training.service.ProductService;
import com.training.utility.AddressInfo;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private AddressInfo addressInfo;
	
	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable int id) {
		Product product = productService.getProduct(id);
		product.setServiceAddress(addressInfo.getServiceAddress());
		return product;
	}
	
}
