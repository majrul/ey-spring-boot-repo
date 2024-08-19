package com.training.message;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.training.service.OrderService;

@Configuration
public class MessageProcessor {

	@Autowired
	private OrderService orderService;
	
	@Bean
	public Consumer<Message<Integer, OrderDetails>> messageConsumer() {
		return message -> {
			System.out.println("processing incoming message created at " + message.getMessageCreatedAt());
			
			switch (message.getMessageType()) {
			case CREATE: {
				OrderDetails orderDetails = message.getData();
				System.out.println("processing create order request for customer id " + orderDetails.getCustomerId());
				orderService.placeOrder(orderDetails);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + message.getMessageType());
			}
		};
	}
}
