package com.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.message.OrderDetails;
import com.training.service.CustomerOrderService;

@RestController
public class CustomerOrderController {

	@Autowired
	private CustomerOrderService customerOrderService;
	
	@PostMapping("/customer/order")
	public String placeOrder(@RequestBody OrderDetails orderDetails) {
		customerOrderService.placeOrder(orderDetails);
		return "Done!";
	}
	
}
