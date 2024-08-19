package com.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.training.message.Message;
import com.training.message.Message.Type;
import com.training.message.OrderDetails;

@Service
public class CustomerOrderService {

	@Autowired
	private StreamBridge streamBridge;
	
	public void placeOrder(OrderDetails orderDetails) {
		Message<Integer, OrderDetails> message = new Message<>(Type.CREATE, null, orderDetails);
		streamBridge.send("order-out-0", message);
		System.out.printf("Sending a %s message to %s", message.getMessageType(), "order");
	}
}
