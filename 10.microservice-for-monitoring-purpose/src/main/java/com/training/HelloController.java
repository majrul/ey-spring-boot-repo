package com.training;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("")
	public String findAll() {
		return "Hello World!";
	}

}
