package com.shoppingMall.mypage.entity;

import com.shoppingMall.admin.domain.ItemOption;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WishItem {
	
	private Wish wish;

	private ItemOption option;
	
}
