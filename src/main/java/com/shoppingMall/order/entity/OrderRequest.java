package com.shoppingMall.order.entity;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {

	private List<OrderItem> orderItem;
	private int totalPrice;
}
