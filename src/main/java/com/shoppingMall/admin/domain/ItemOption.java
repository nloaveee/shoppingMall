package com.shoppingMall.admin.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ItemOption {
	
	private int id;
	private int itemId;
	private String size;
	private String color;
	private int stock;
}
