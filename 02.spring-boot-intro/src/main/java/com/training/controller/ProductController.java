package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.Status;
import com.training.entity.Product;
import com.training.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/product/all")
	public List<Product> fetchAll() {
		return productService.fetchAll();
	}
	
	//http://localhost:8080/product/2
	@GetMapping("/product/{id}")
	public Product fetch(@PathVariable int id) {
		return productService.fetch(id);
	}

	//http://localhost:8080/productv2?id=2
	@GetMapping("/productv2")
	public Product fetchv2(@RequestParam int id) {
		return productService.fetch(id);
	}

	//http://localhost:8080/productv3/anything;id=2
	@GetMapping("/productv3/{placeholder}")
	public Product fetchv3(@PathVariable String placeholder, @MatrixVariable int id) {
		return productService.fetch(id);
	}

	/*@PostMapping("/product/add")
	public String add(@RequestBody Product product) {
		int id = productService.add(product);
		return "Product added with id " + id;
	}*/
	
	@PostMapping("/product/add")
	public Status add(@RequestBody Product product) {
		int id = productService.add(product);
		//return "Product added with id " + id;
		
		Status status = new Status();
		status.setStatus(true);
		status.setMessageIfAny("Product addded!");
		status.setProductId(id);
		return status;
	}
}


