package com.training.service;

import org.springframework.stereotype.Service;

import com.training.message.OrderDetails;

@Service
public class OrderService {

	public void placeOrder(OrderDetails orderDetails) {
		System.out.println("some business logic of order processing here..");
	}
}
