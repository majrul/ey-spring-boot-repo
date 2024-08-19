package com.training.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shopping_order")
@Getter
@Setter
public class Order {

	@Id
	@GeneratedValue
	private int id;

	private LocalDateTime dateAndTime;
	private double amount;
	
	@OneToMany(mappedBy = "order")
	private List<LineItem> lineItems;
	
	
}
