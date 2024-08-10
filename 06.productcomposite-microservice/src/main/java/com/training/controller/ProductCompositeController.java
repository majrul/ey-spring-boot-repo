package com.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.training.entity.ProductAggregate;
import com.training.service.ProductCompositeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ProductCompositeController {

	@Autowired
	private ProductCompositeService productCompositeService;
	
	@GetMapping("/product-info/{productId}")
	public ProductAggregate getProductInfo(@PathVariable int productId) {
		return productCompositeService.getProduct(productId);
	}
	
}
