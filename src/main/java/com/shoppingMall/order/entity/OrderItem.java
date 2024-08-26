package com.shoppingMall.order.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderItem {

	private String name;
	private String option;
	private int count;
	private int sale;
	
}
