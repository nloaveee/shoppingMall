package com.shoppingMall.mypage.entity;

import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CartItem {
	
	 private int itemId;
	 private String option;
	 private int quantity;

}
