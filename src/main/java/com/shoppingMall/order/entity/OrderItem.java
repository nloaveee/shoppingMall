package com.shoppingMall.order.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderItem {

	private int itemId;
	private String option;
	private int count;
	private int sale;
	
}
