package com.training.message;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetails {

	private int customerId;
	
	private List<OrderItem> orderItems;
	
	
}
