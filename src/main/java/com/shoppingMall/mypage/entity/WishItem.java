package com.shoppingMall.mypage.entity;

import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;

import lombok.Data;

@Data
public class WishItem {

	 private int itemId;
	 private String option;
	 
	 private Item item;
	 private ItemOption itemOption;
}
