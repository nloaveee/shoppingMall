package com.shoppingMall.order.entity;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderView {
	
	private Orders orders;

	private List<OrderItem> orderItemList;
}
