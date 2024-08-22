package com.shoppingMall.order.entity;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {

	private List<OrderList> orderList;
	private int totalPrice;
}
