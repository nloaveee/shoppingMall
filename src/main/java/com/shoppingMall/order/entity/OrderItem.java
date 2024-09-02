package com.shoppingMall.order.entity;

import com.shoppingMall.admin.domain.Item;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderItem {

	private int itemId;
	private String option;
	private int count;
	private int sale;
	
	private Item item;  // Item 객체 추가
	
}
