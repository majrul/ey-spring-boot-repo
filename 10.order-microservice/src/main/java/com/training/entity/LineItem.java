package com.training.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LineItem {

	@Id
	@GeneratedValue
	private int id;
	
	private int productId;
	private int quantity;
	private double subTotal;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	
}
