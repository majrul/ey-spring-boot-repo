package com.training.component;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ProductController {

	@GetMapping("/product")
	public @ResponseBody Product getProduct() {
		return new Product(101, "iPhone", 79000);
	}
	
}
